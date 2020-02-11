package cn.fuzej.blog.web.admin;

import cn.fuzej.blog.service.TypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by fuzej on  2020-02-09 20:47
 *
 * @Describtion :
 */
@Controller
@RequestMapping("/adminaa")
public class TypeController1
{
    private TypeService typeService;

    @GetMapping("/typess")
    public String list(
            @PageableDefault(
                    size = 10, sort = {"id"},
                    direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model)
    {
        /*思路
        *   参数：
        *       1.默认设置 排序、页容
        *       2.pageable 是什么
        *           从前端获取的数据
        * */

       model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    //返回一个新增的页面  GET 请求
    @GetMapping("/types/input")
    public String input()
    {
        return "admin/types-input";
    }
}
