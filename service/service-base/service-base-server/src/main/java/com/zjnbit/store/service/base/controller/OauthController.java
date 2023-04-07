package com.zjnbit.store.service.base.controller;

import com.zjnbit.store.framework.model.base.param.OauthSigninParam;
import com.zjnbit.store.framework.model.base.param.OauthSignupCodeParam;
import com.zjnbit.store.framework.model.base.param.OauthSignupParam;
import com.zjnbit.store.framework.model.base.vo.OauthSigninVo;
import com.zjnbit.store.framework.web.controller.BaseController;
import com.zjnbit.store.framework.web.model.Result;
import com.zjnbit.store.service.base.service.OauthService;
import lombok.SneakyThrows;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chenjy
 * @emp chenjy 
 * @date 2023/3/28 11:04
 * @Description
 **/
@RestController
@RequestMapping("/oauth")
public class OauthController extends BaseController<OauthService> {

    @PostMapping(value="/signup/code")
    public Result<Boolean> signupCode(@RequestBody OauthSignupCodeParam param) {
        return Result.success(baseService.signupCode(param));
    }

    @PostMapping(value = "/signup")
    public Result<OauthSigninVo> signup(@RequestBody @Validated OauthSignupParam param) {
        return Result.success(baseService.signup(param));
    }

    @PostMapping(value = "/signin")
    public Result<OauthSigninVo> signin(@RequestBody @Validated OauthSigninParam param) {
        return Result.success(baseService.signin(param));
    }



    @GetMapping("/render/{source}")
    @SneakyThrows
    public void render(@PathVariable("source") String source, HttpServletResponse response) {
        response.sendRedirect(baseService.getRenderUrlBySource(source));
    }

    @GetMapping("/callback/{source}")
    public Result<OauthSigninVo> login(@PathVariable("source") String source, AuthCallback callback) {
        OauthSigninVo oauthSigninVo = baseService.signinByThird(source, callback);
        if (null != oauthSigninVo) {
            return Result.success(oauthSigninVo);
        } else {
            return new Result<>("A0202", "账户未注册", null, null);
        }
    }


}