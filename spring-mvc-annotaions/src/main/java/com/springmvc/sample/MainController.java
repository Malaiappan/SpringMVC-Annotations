
package com.springmvc.sample;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Spring MVC controller
 * 
 * @author Malaiappan
 * @since 19-Sep-2013
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {

    /**
     * Gets the hello spring.
     * 
     * @return the view name hello spring
     */
    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String getHelloSpring() {

        return "helloSpring";
    }

    /**
     * Sample usage of {@code @PathVariable}
     * 
     * @param pathVariable the path variable
     * @return the hello path variable
     */
    @RequestMapping(value = "/sample/{pathVariable}", method = RequestMethod.GET)
    public String getHelloPathVariable(Model model, @PathVariable
    String pathVariable) {

        model.addAttribute("pathVariable", pathVariable);
        return "helloSpring";
    }

    /**
     * Gets the login form.
     * 
     * @return the login form
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {

        return "login";
    }
    
    /**
     * Get the form as json
     * @return the loging form
     */
    @RequestMapping(value = "/login/form", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LoginForm getForm() {
    	LoginForm loginForm = new LoginForm();
    	loginForm.setUserName("userName");
    	loginForm.setPassWord("****");
		return loginForm;
    }

    @RequestMapping(value = "/passenger", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Passenger getPassenger() {
		return new Passenger("asdf", "asdf");
    }
    
    /**
     * Validate login.<br>
     * Sample usage of {@code @RequestParam} and {@code ModelAndView}
     * 
     * @param userName the user name
     * @param passWord the pass word
     * @return the model and view
     */
    @RequestMapping(value = "/login/submit", method = RequestMethod.POST)
    public ModelAndView validateLogin(@RequestParam("userName")
    String userName, @RequestParam("passWord")
    String passWord) {

        if (userName.equals("admin") && passWord.equals("pwd")) {
            return new ModelAndView("helloSpring", "welcomeMessage", "Welcome Admin");
        }
        return new ModelAndView("login", "errorMessage", "Login Failed");
    }

    /**
     * Validate login. <br>
     * Sample usage of {@code @ModelAttribute} and {@code ModelAndView}
     * 
     * @param loginForm the login form
     * @return the model and view
     */
    @RequestMapping(value = "/login/submitt", method = RequestMethod.POST)
    public ModelAndView validateLogin(@ModelAttribute("loginForm")
    LoginForm loginForm) {

        if (loginForm.getUserName().equals("admin") && loginForm.getPassWord().equals("pwd")) {
            return new ModelAndView("helloSpring", "welcomeMessage", "Welcome Admin");
        }
        return new ModelAndView("login", "errorMessage", "Login Failed");
    }

    /**
     * Go to google. <br>
     * Sample usage of {@code RedirectView}
     * 
     * @return the redirect view
     */
    @RequestMapping(value = "/goToGoogle", method = RequestMethod.GET)
    public RedirectView goToGoogle() {

        return new RedirectView("http://www.google.com");
    }

    /**
     * Gets the model attribute. <br>
     * Sample usage of {@code @ModelAttribute} in method level
     * 
     * @return the model attribute
     */
    @ModelAttribute("common")
    public String getModelAttribute() {

        return "spring";
    }

    /**
     * Throw exceptions.
     * 
     * @return the string
     */
    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public String throwExceptions() {

        throw new RuntimeException();

    }

    /**
     * Handle exceptions.<br>
     * Sample usage of {@code @ExceptionHandler}
     * 
     * @param runtimeException the runtime exception
     * @return the string
     */
    @ExceptionHandler({RuntimeException.class})
    public String handleExceptions(RuntimeException runtimeException) {

        return "redirect:/main/login";
    }

}
