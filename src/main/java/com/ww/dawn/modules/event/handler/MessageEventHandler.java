package com.ww.dawn.modules.event.handler;

import com.ww.dawn.base.lang.Consts;
import com.ww.dawn.modules.data.MessageVO;
import com.ww.dawn.modules.data.PostVO;
import com.ww.dawn.modules.entity.Comment;
import com.ww.dawn.modules.event.MessageEvent;
import com.ww.dawn.modules.service.CommentService;
import com.ww.dawn.modules.service.MessageService;
import com.ww.dawn.modules.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author langhsu on 2015/8/31.
 */
@Component
public class MessageEventHandler implements ApplicationListener<MessageEvent> {
    @Autowired
    private MessageService messageService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @Async
    @Override
    public void onApplicationEvent(MessageEvent event) {
        MessageVO nt = new MessageVO();
        nt.setPostId(event.getPostId());
        nt.setFromId(event.getFromUserId());
        nt.setEvent(event.getEvent());

        PostVO p;
        switch (event.getEvent()) {
            case Consts.MESSAGE_EVENT_FAVOR_POST:
                p = postService.get(event.getPostId());
                Assert.notNull(p, "文章不存在");
                nt.setUserId(p.getAuthorId());
                break;
            case Consts.MESSAGE_EVENT_COMMENT:
                p = postService.get(event.getPostId());
                Assert.notNull(p, "文章不存在");
                nt.setUserId(p.getAuthorId());
                // 自增评论数
                postService.identityComments(event.getPostId());
                break;
            case Consts.MESSAGE_EVENT_COMMENT_REPLY:
                Comment c = commentService.findById(event.getCommentParentId());
                Assert.notNull(c, "回复的评论不存在");
                nt.setUserId(c.getAuthorId());

                // 自增评论数
                postService.identityComments(event.getPostId());
                break;
            default:
                nt.setUserId(event.getToUserId());
        }

        messageService.send(nt);
    }
}
