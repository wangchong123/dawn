package com.ww.dawn.web.controller.admin;

import com.ww.dawn.modules.data.PermissionTree;
import com.ww.dawn.modules.service.PermissionService;
import com.ww.dawn.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author - langhsu
 * @create - 2018/5/18
 */
@RestController
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/tree")
    public List<PermissionTree> tree() {
        return permissionService.tree();
    }
}
