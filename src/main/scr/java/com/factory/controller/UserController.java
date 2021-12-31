package com.factory.controller;

import com.factory.mapping.UserRepostory;
import com.factory.pojo.User;
import com.factory.util.md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepostory userRepostory;




    @RequestMapping("/login")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> userLogin(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,@RequestParam(value = "lastdate")String lastdate) throws IOException{
        //创建令牌token
        String token= UUID.randomUUID().toString().substring(0,16);
        //根据用户名查找
        List<User> usernames = userRepostory.findByUsername(username);
        //判断有没有找到 没有找到返回400 找到了继续执行下一条
        if (usernames.size()==0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //获取一条数据
        User user = usernames.get(0);


        //根据数据库里的密码 对比 前端得到的密码 + 数据库里的盐 合成
        if (user.getPassword().equals(md5Util.getHMAC(password,user.getSalt()))){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                //下单日期
                if (lastdate!="" && lastdate != null){
                    user.setLastdate(format.parse(lastdate));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            userRepostory.save(user);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping("/quickUser")
    @ResponseBody
    @Transactional
    public User quickUser(@RequestParam(value = "username") String username){
        List<User> usernames = userRepostory.findByUsername(username);
        //获取一条数据
        User user = usernames.get(0);
        return user;
    }

    @RequestMapping("/register")
    @ResponseBody
    @Transactional
    public ResponseEntity<Boolean> userRegister(
            @RequestParam(value = "username") String username,@RequestParam(value = "password") String password,
            @RequestParam(value = "f_power") String f_power,@RequestParam(value = "w_power") String w_power,
            @RequestParam(value = "g_power") String g_power,@RequestParam(value = "s_power") String s_power,
            @RequestParam(value = "u_power") String u_power,@RequestParam(value = "name") String name
    ) throws Exception{
        //随机盐
        List<User> all = userRepostory.findAll();
        for (User user:all) {
            if (username.equals(user.getUsername())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        User user =new User();
        user.setSalt(md5Util.getRandomSalt());
        user.setPassword(md5Util.getHMAC(password,user.getSalt()));
        user.setUsername(username);
        user.setF_power(f_power);
        user.setW_power(w_power);
        user.setG_power(g_power);
        user.setS_power(s_power);
        user.setU_power(u_power);
        user.setName(name);
        //默认注册图片
        user.setCompany_logo("http://127.0.0.1:8848/api/upload/imgs/wwww.jpg");
        userRepostory.save(user);
        return ResponseEntity.ok(true);
    }

    @RequestMapping("/edit")
    @ResponseBody
    @Transactional
    public ResponseEntity<Boolean> editUser(
            @RequestParam(value = "username") String username,@RequestParam(value = "password") String password,
            @RequestParam(value = "f_power") String f_power,@RequestParam(value = "w_power") String w_power,
            @RequestParam(value = "g_power") String g_power,@RequestParam(value = "s_power") String s_power,
            @RequestParam(value = "u_power") String u_power,@RequestParam(value = "name") String name,
            @RequestParam(value = "u_id")String u_id
    ){
        //随机盐
        User user =new User();
        user.setSalt(md5Util.getRandomSalt());
        user.setPassword(md5Util.getHMAC(password,user.getSalt()));
        user.setUsername(username);
        user.setF_power(f_power);
        user.setW_power(w_power);
        user.setG_power(g_power);
        user.setS_power(s_power);
        user.setU_power(u_power);
        user.setU_id(Long.parseLong(u_id));
        user.setName(name);
        userRepostory.save(user);
        return ResponseEntity.ok(true);
    }

    @RequestMapping("/editPsw")
    @ResponseBody
    @Transactional
    public ResponseEntity<Boolean> editPsw(
            @RequestParam(value = "username") String username,@RequestParam(value = "password") String password,
            @RequestParam(value = "nowpassword")String nowpassword
    ){
        List<User> users = userRepostory.findByUsername(username);
        User user = users.get(0);
        if (user.getPassword().equals(md5Util.getHMAC(password,user.getSalt()))){
            user.setSalt(md5Util.getRandomSalt());
            user.setPassword(md5Util.getHMAC(nowpassword,user.getSalt()));
            userRepostory.save(user);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @RequestMapping("/quick")
    @ResponseBody
    @Transactional
    public List<User> quickUser(){
        List<User> users = userRepostory.findAll();
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Transactional
    public void deleteUser(@RequestParam(value = "u_id")String u_id){
        User user=new User();
        user.setU_id(Long.parseLong(u_id));
        userRepostory.delete(user);
    }

    @RequestMapping("/addPhoto")
    @ResponseBody
    @Transactional
    public ResponseEntity<Boolean> addPhoto(@RequestParam(value = "file",required = false) MultipartFile file,@RequestParam(value = "username",required = false)String username,
                                            @RequestParam(value = "newname",required = false)String newname,@RequestParam(value = "oldname")String oldname) throws IOException {
        List<User> users = userRepostory.findByUsername(username);
        if (users.size()==0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User user = users.get(0);
        if (!oldname.equals(newname)){
            user.setName(newname);
        }
        byte [] data;
        FileOutputStream fos;
        if (file!=null){
            //打印的位置
            String token= UUID.randomUUID().toString().substring(0,8);
            fos=new FileOutputStream("imgs/" +token+".jpg");

            data=file.getBytes();

            user.setCompany_logo("http://127.0.0.1:8848/api/upload/imgs/"+token+".jpg");
            for (int i=0;i<data.length;i++){
                fos.write(data[i]);
            }
        }
        userRepostory.save(user);
        return ResponseEntity.ok(true);
    }
}
