package com.example.mymovie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymovie.utils.KeyBoardUtil;


public class LoginActivity extends Activity {


    /**
     * @ClassName: LoginActivity
     * @Author SYT
     * @Description : 登录界面
     * @Date 14:21 2019/6/10
     **/
    EditText loginUserName;
    EditText loginPassWord;
    Button Btn_Confirm;
    Button Btn_regist;

    private String UserName; //从注册页面获取的用户信息，初始为null，只有当RegistActivity传值回来才会被赋值，否则一直为空；
    private String UserPsw;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == 0){
            if (data!=null) {
                Bundle bundle = data.getExtras();
                UserName = bundle.getString("UserName");
                UserPsw = bundle.getString("UserPsw");
                loginUserName.setText(UserName);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /**
         * @Author SYT
         * @Description 重写该方法用于点击空白隐藏键盘
         * @Date 17:55 2019/6/10
         * @Param [ev]
         * @return boolean
         **/

        KeyBoardUtil.hideInputWhenTouchOtherView(this, ev, null);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initView();//初始化组件
        setOnListener();//为盖activity下组件绑定监听

    }



    private void initView(){
        /**
         * @Author SYT
         * @Description 初始化Loginactivity 下的所属组件 
         * @Date 14:13 2019/6/10
         * @Param []
         * @return void
         **/
        loginUserName = findViewById(R.id.edt_username);
        loginPassWord = findViewById(R.id.edt_pwd);
        Btn_Confirm = findViewById(R.id.btn_login);
        Btn_regist = findViewById(R.id.btn_register);

    }

    private void setOnListener(){
        /**
         * @Author SYT
         * @Description 为activity下组件绑定监听
         * @Date 17:54 2019/6/10
         * @Param []
         * @return void
         **/
        Btn_regist.setOnClickListener(new LoginActivity_ViewOnClickListner());//为登录按钮绑定监听器
        loginUserName.setOnClickListener(new LoginActivity_ViewOnClickListner());
        loginPassWord.setOnClickListener(new LoginActivity_ViewOnClickListner());
        Btn_Confirm.setOnClickListener(new LoginActivity_ViewOnClickListner());
    }



    private class LoginActivity_ViewOnClickListner implements View.OnClickListener{

        /**
         * @ClassName: LoginActivity_ViewOnClickListner
         * @Author SYT
         * @Description :成员内部类，为LoginActivity下的所属组件绑定Listener
         * @Date 14:21 2019/6/10
         **/


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_register:// 点击注册按钮启动RegisterActivity
                    startActivityForResult(new Intent(LoginActivity.this , RegistActivity.class),0);
                    break;
                case R.id.edt_username: //输入用户名时弹出键盘
                    loginUserName.setFocusable(true);
                    loginUserName.setFocusableInTouchMode(true);
                    loginUserName.requestFocus();
                    loginUserName.findFocus();
                    InputMethodManager imm1 = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm1.showSoftInput(loginUserName, InputMethodManager.SHOW_FORCED);// 显示软键盘
                    break;

                case R.id.edt_pwd: // 输入密码时弹出键盘
                    loginPassWord.setFocusable(true);
                    loginPassWord.setFocusableInTouchMode(true);
                    loginPassWord.requestFocus();
                    loginPassWord.findFocus();
                    InputMethodManager imm2 = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm2.showSoftInput(loginPassWord, InputMethodManager.SHOW_FORCED);// 显示软键盘
                    break;
                case R.id.btn_login:
                    if (loginPassWord == null || loginUserName == null || UserName == null || UserPsw==null){
                        Toast.makeText(LoginActivity.this,"输入不能为空，请先注册", Toast.LENGTH_SHORT ).show();
                    }
                    else if (loginPassWord.getText().toString().length()==0 || loginUserName.getText().toString().length()==0 ){
                        Toast.makeText(LoginActivity.this,"输入不能为空，请先注册", Toast.LENGTH_SHORT ).show();
                    }

                    else if (UserPsw.equals(loginPassWord.getText().toString())  && UserName.equals(loginUserName.getText().toString())){ //这里注意要用equals判断字符串的值是否相等
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                    else if (loginPassWord.getText().toString().length()==0 || loginUserName.getText().toString().length()==0)
                        Toast.makeText(LoginActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(LoginActivity.this, "密码有误！", Toast.LENGTH_SHORT).show();

                    break;

            }
        }
    }

}



