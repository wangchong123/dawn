package com.ww.dawn.modules.data;

import com.ww.dawn.modules.entity.Favorite;

/**
 * @author langhsu on 2015/8/31.
 */
public class FavoriteVO extends Favorite {
    // extend
    private PostVO post;

    public PostVO getPost() {
        return post;
    }

    public void setPost(PostVO post) {
        this.post = post;
    }
}
