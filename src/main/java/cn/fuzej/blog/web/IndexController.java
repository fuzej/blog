package cn.fuzej.blog.web;

import cn.fuzej.blog.service.BlogService;
import cn.fuzej.blog.service.TagService;
import cn.fuzej.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author fuzej
 * @date 2020-02-03 10:43
 * @ClassName
 * @Describtion
 */
@Controller
public class IndexController
{
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(
            @PageableDefault(
                    size = 5,
                    sort = {"updateTime"},
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            Model model)
    {
        model.addAttribute("page", blogService.listBlog(pageable));
        //分类
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));

        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));

        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable String id)
    {

        return "blog";
    }
}
