package com.projects.modular.api.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.projects.config.web.ResponseData;
import com.projects.core.common.constant.factory.ConstantFactory;
import com.projects.core.common.exception.BizExceptionEnum;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.Area;
import com.projects.modular.api.entity.Chucao;
import com.projects.modular.api.entity.Device;
import com.projects.modular.api.entity.Fix;
import com.projects.modular.api.entity.Ground;
import com.projects.modular.api.entity.MapLog;
import com.projects.modular.api.entity.Nongyao;
import com.projects.modular.api.entity.RegisterUser;
import com.projects.modular.api.entity.Weihai;
import com.projects.modular.api.model.params.AreaParam;
import com.projects.modular.api.model.params.ChucaoParam;
import com.projects.modular.api.model.params.DeviceParam;
import com.projects.modular.api.model.params.FixParam;
import com.projects.modular.api.model.params.GroundParam;
import com.projects.modular.api.model.params.MapLogParam;
import com.projects.modular.api.model.params.NongyaoParam;
import com.projects.modular.api.model.params.NoticeParam;
import com.projects.modular.api.model.params.RegisterUserParam;
import com.projects.modular.api.model.params.WeihaiParam;
import com.projects.modular.api.service.AreaService;
import com.projects.modular.api.service.ChucaoService;
import com.projects.modular.api.service.DeviceService;
import com.projects.modular.api.service.FixService;
import com.projects.modular.api.service.GroundService;
import com.projects.modular.api.service.MapLogService;
import com.projects.modular.api.service.NongyaoService;
import com.projects.modular.api.service.RegisterUserService;
import com.projects.modular.api.service.WeihaiService;
import com.projects.modular.comment.entity.Comment;
import com.projects.modular.comment.entity.CommentData;
import com.projects.modular.comment.model.params.CommentParam;
import com.projects.modular.comment.service.CommentService;
import com.projects.modular.system.entity.User;
import com.projects.modular.system.service.UserService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;


/**
 * 接口控制器提供
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {
    @Autowired
    private RegisterUserService registerUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentServic;
    @Autowired
    private GroundService groundService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private MapLogService mapLogService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private ChucaoService chucaoService;
    @Autowired
    private NongyaoService nongyaoService;
    @Autowired
    private WeihaiService weihaiService;

    @PostMapping(value = "/registerUser")
    public ResponseData registerUser(@RequestBody RegisterUserParam registerUserParam) {

        try {
            QueryWrapper<RegisterUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userName", registerUserParam.getUserName());
            RegisterUser registerUser = registerUserService.getOne(queryWrapper);
            if (null != registerUser) {
                return ResponseData.error("用户已注册");
            }

            registerUserService.add(registerUserParam);

            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }


    @PostMapping(value = "/editUser")
    public ResponseData editUser(@RequestBody RegisterUserParam registerUserParam) {

        try {
            RegisterUser user = new RegisterUser();
            UpdateWrapper<RegisterUser> queryWrapper = new UpdateWrapper<>();
            queryWrapper.eq("userName", registerUserParam.getUserName());
            ToolUtil.copyProperties(registerUserParam, user);
            registerUserService.update(user, queryWrapper);

            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    /**
     * 修改当前用户的密码
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("userId") Long userId) {
        if (ToolUtil.isOneEmpty(oldPassword, newPassword)) {
            throw new RequestEmptyException();
        }
        this.registerUserService.changePwd(oldPassword, newPassword, userId);
        return ResponseData.success();
    }

    @PostMapping(value = "/getUserList")
    public ResponseData getUserList() {

        try {
            return ResponseData.success(registerUserService.findListBySpec(null));
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }


    @PostMapping(value = "/loginUser")
    public ResponseData loginrUser(@RequestBody RegisterUserParam registerUserParam) {

        try {
            QueryWrapper<RegisterUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userName", registerUserParam.getUserName());
            RegisterUser registerUser = registerUserService.getOne(queryWrapper);
            if (null == registerUser) {//如果没有 则检查 管理员表有没有 如果有 则返回成功
                User user = userService.getByAccount(registerUserParam.getUserName());
                if (user != null) {
                    RegisterUser temp = new RegisterUser();
                    temp.setAdmin(true);
                    temp.setName(user.getName());
                    temp.setUserName(user.getAccount());
                    temp.setHeadImage(user.getAvatar());
                    temp.setEmail(user.getEmail());
                    temp.setSex(user.getSex());
                    return ResponseData.success(200, "登录成功", temp);
                }

                return ResponseData.error("用户不存在");
            }

            if (!registerUser.getUserPass().equals(registerUserParam.getUserPass())) {
                return ResponseData.error("密码错误");
            }
            return ResponseData.success(200, "登录成功", registerUser);
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }


    @PostMapping(value = "/setHeadImage")
    public ResponseData setHeadImage(@RequestBody RegisterUserParam registerUserParam) {

        try {
            UpdateWrapper<RegisterUser> queryWrapper = new UpdateWrapper<>();
            queryWrapper.eq("userName", registerUserParam.getUserName());
            RegisterUser user = new RegisterUser();
            user.setHeadImage(registerUserParam.getHeadImage());
            registerUserService.update(user, queryWrapper);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }


    @RequestMapping(value = "/upload")
    @ResponseBody
    public ResponseData imgUpload(@RequestParam(value = "uploadfile", required = false) MultipartFile[] uploadfile) {
        List<String> pics = new ArrayList<>();
        for (MultipartFile file : uploadfile) {
            String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(file.getOriginalFilename());
            try {
                ServletContext sc = this.getHttpServletRequest().getServletContext();
                String fileSavePath = sc.getRealPath("/assets/upload/");
                file.transferTo(new File(fileSavePath + pictureName));
                pics.add(pictureName);
            } catch (Exception e) {
                throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
            }

        }

        return ResponseData.success(200, "上传成功", pics);
    }


    @PostMapping(value = "/saveComment")
    public ResponseData saveComment(@RequestBody CommentParam commentParam) {

        try {
            commentServic.add(commentParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/saveGround")
    public ResponseData saveComment(@RequestBody GroundParam groundParam) {
        try {
            groundService.add(groundParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/deleteGround")
    public ResponseData deleteGround(@RequestBody GroundParam groundParam) {
        try {
            groundService.delete(groundParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/editGround")
    public ResponseData editGround(@RequestBody GroundParam groundParam) {
        try {
            groundService.update(groundParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/getGroundList")
    public ResponseData getGroundList(@RequestParam Long userId) {
        try {

            QueryWrapper<Ground> queryWrapper = new QueryWrapper<>();
            if (userId != null) {
                queryWrapper.eq("user_id", userId);
            }
            List<Ground> grounds = groundService.list(queryWrapper);
            List<GroundParam> params = new ArrayList<>();
            for (int i = 0; i < grounds.size(); i++) {
                GroundParam param = new GroundParam();
                ToolUtil.copyProperties(grounds.get(i), param);
                param.setAreaName(ConstantFactory.me().getAreaNameById(param.getAreaId()));
                param.setUserName(ConstantFactory.me().getRegUserAccountById(param.getUserId()));
                params.add(param);
            }
            return ResponseData.success(params);
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/saveArea")
    public ResponseData saveArea(@RequestBody AreaParam areaParam) {
        try {
            areaService.add(areaParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/saveMapLog")
    public ResponseData saveMapLog(@RequestBody MapLogParam mapLogParam) {
        try {
            mapLogService.add(mapLogParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @GetMapping(value = "/getDeviceStatus")
    @ResponseBody
    public ResponseData getDeviceStatus(Long deviceId) {
        try {
            return ResponseData.success(deviceService.getById(deviceId));
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/getMapLog")
    @ResponseBody
    public ResponseData getMapLog(String indexId) {
        try {
            QueryWrapper<MapLog> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("indexId", indexId);
            queryWrapper.orderByDesc("time");
            return ResponseData.success(mapLogService.list(queryWrapper));
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/getMapLogList")
    @ResponseBody
    public ResponseData getMapLogList(Long deviceId) {
        try {
            QueryWrapper<MapLog> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("device_id", deviceId);
            queryWrapper.groupBy("indexId");
            queryWrapper.orderByDesc("time");
            return ResponseData.success(mapLogService.list(queryWrapper));
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @GetMapping(value = "/getDeviceList")
    @ResponseBody
    public ResponseData getDeviceList() {
        try {
            return ResponseData.success(deviceService.list());
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/setDeviceStatus")
    public ResponseData setDeviceStatus(@RequestBody DeviceParam device) {
        try {
            deviceService.update(device);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/deleteArea")
    public ResponseData deleteArea(@RequestBody AreaParam areaParam) {
        try {
            areaService.delete(areaParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/editArea")
    public ResponseData editArea(@RequestBody AreaParam areaParam) {
        try {
            areaService.update(areaParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/getAreaList")
    public ResponseData getAreaList() {
        try {

            return ResponseData.success(areaService.list());
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    /***
     * 除草
     * @return
     */
    @PostMapping(value = "/saveChuCao")
    public ResponseData saveChuCao(@RequestBody ChucaoParam chucaoParam) {
        try {
            chucaoService.add(chucaoParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/deleteChuCao")
    public ResponseData deleteChuCao(@RequestBody ChucaoParam chucaoParam) {
        try {
            chucaoService.delete(chucaoParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/editChuCao")
    public ResponseData editChuCao(@RequestBody ChucaoParam chucaoParam) {
        try {
            chucaoService.update(chucaoParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/getChuCaoList")
    public ResponseData getChuCaoList(@RequestParam Long gid) {
        try {
            QueryWrapper<Chucao> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("gid", gid);
            return ResponseData.success(chucaoService.list(queryWrapper));
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    /***
     * 农药
     * @return
     */
    @PostMapping(value = "/saveNongYao")
    public ResponseData saveNongYao(@RequestBody NongyaoParam nongyaoParam) {
        try {
            nongyaoService.add(nongyaoParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/deleteNongYao")
    public ResponseData deleteNongYao(@RequestBody NongyaoParam nongyaoParam) {
        try {
            nongyaoService.delete(nongyaoParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/editNongYao")
    public ResponseData editNongYao(@RequestBody NongyaoParam nongyaoParam) {
        try {
            nongyaoService.update(nongyaoParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/getNongYaoList")
    public ResponseData getNongYaoList(@RequestParam Long gid) {
        try {

            QueryWrapper<Nongyao> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("gid", gid);
            return ResponseData.success(nongyaoService.list(queryWrapper));
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    /***
     * 危害
     * @return
     */
    @PostMapping(value = "/saveWeiHai")
    public ResponseData saveWeihai(@RequestBody WeihaiParam weihaiParam) {
        try {
            weihaiService.add(weihaiParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/deleteWeihai")
    public ResponseData deleteWeihai(@RequestBody WeihaiParam weihaiParam) {
        try {
            weihaiService.delete(weihaiParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/editWeihai")
    public ResponseData editWeihai(@RequestBody WeihaiParam weihaiParam) {
        try {
            weihaiService.update(weihaiParam);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @PostMapping(value = "/getWeiHaiList")
    public ResponseData getWeihaiList(@RequestParam Long gid) {
        try {

            QueryWrapper<Weihai> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("gid", gid);
            return ResponseData.success(weihaiService.list(queryWrapper));
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }

    @GetMapping(value = "/getCommentList")
    public ResponseData getCommentList(Long lostId) {

        try {
            List<Comment> list = commentServic.getCommentList(lostId);
            CommentData data = new CommentData();
            data.list = (null == list) ? new ArrayList<>() : list;
            return ResponseData.success(data);
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }

    }


}
