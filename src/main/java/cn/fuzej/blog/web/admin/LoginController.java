package cn.fuzej.blog.web.admin;

import cn.fuzej.blog.po.User;
import cn.fuzej.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * created by fuzej on  2020-02-09 17:29
 *
 * @Describtion :
 */
@Controller
@RequestMapping("/admin")
public class LoginController
{
    @Autowired
    private UserService userService;

    /*
    * @GetMapping 、@RequestMapping 、@ResponseBody 的区别
    * */
    @GetMapping
    public String loginPage()
    {
        return "admin/login";
    }

    /*
     * HttpSession String或者 HttpServletRequest request
     *
     * */
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes attributes)
    {


        User user = userService.checkUser(username, password);
        if (user != null)
        {
            user.setPassword(null);//在页面拿到密码不安全
            session.setAttribute("user", user);
            return "admin/index";
        } else
        {
            /*
            * 不能传一个参数Model ？？？重定向拿不到——》
            * 那转发 ——》 再次登录的话存在路径问题
            *
            * */
            attributes.addFlashAttribute("message", "密码！！！");
            //return "admin/login"; 重定向有什么区别:再次登录的话，路径有问题?
            return "redirect:/admin"; // ——》admin下默认访问index.html
        }
    }

    @GetMapping("/loginout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
