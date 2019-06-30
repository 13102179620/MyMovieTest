package com.example.mymovie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymovie.utils.KeyBoardUtil;


public class RegistActivity extends AppCompatActivity {

    /**
     * @ClassName: RegistActivity
     * @Author SYT
     * @Description 注册界面activity
     * @Date 20:56 2019/6/10
     **/


    EditText EditTextUserName;
    EditText EditTextUserPsw;
    Button Btn_ConfirmRegist;




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
        setContentView(R.layout.regist_activity);
        initView();
        setOnListener();





    }



    private void setOnListener() {
        /**
         * @Author SYT
         * @Description 绑定事件监听器
         * @Date 21:06 2019/6/10
         * @Param * @param
         * @return void
         **/

        EditTextUserName.setOnClickListener(new RegistActivity_ViewOnClickListner());
        EditTextUserPsw.setOnClickListener(new RegistActivity_ViewOnClickListner());
        Btn_ConfirmRegist.setOnClickListener(new RegistActivity_ViewOnClickListner());
    }


    private void initView() {
        /**
         * @Author SYT
         * @Description 初始化组件
         * @Date 20:58 2019/6/10
         * @Param * @param
         * @return void
         **/
        EditTextUserName = findViewById(R.id.editTextUserName);
        EditTextUserPsw = findViewById(R.id.editTextUserPsw);
        Btn_ConfirmRegist = findViewById(R.id.btnSubmit);

    }




    private class RegistActivity_ViewOnClickListner implements View.OnClickListener{

        /**
         * @ClassName: LoginActivity_ViewOnClickListner
         * @Author SYT
         * @Description :成员内部类，为RegistActivity下的所属组件绑定Listener
         * @Date 14:21 2019/6/10
         **/


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnSubmit:// 点击注册按钮启动RegisterActivity
                    if (editTextIsEmpty()) {
                        Intent LoginSend_intent = getIntent();
                        Bundle bundleUserInfo = new Bundle();
                        bundleUserInfo.putString("UserName", EditTextUserName.getText().toString());
                        bundleUserInfo.putString("UserPsw", EditTextUserPsw.getText().toString());
                        LoginSend_intent.putExtras(bundleUserInfo);
                        RegistActivity.this.setResult(0, LoginSend_intent);
                        RegistActivity.this.finish();
                    }else {
                        Toast.makeText(RegistActivity.this, "注册信息不能为空！", Toast.LENGTH_SHORT).show();
                    }

                    break;


                case R.id.editTextUserName: //输入用户名时弹出键盘
                    EditTextUserName.setFocusable(true);
                    EditTextUserName.setFocusableInTouchMode(true);
                    EditTextUserName.requestFocus();
                    EditTextUserName.findFocus();
                    InputMethodManager imm1 = (InputMethodManager) RegistActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm1.showSoftInput(EditTextUserName, InputMethodManager.SHOW_FORCED);// 显示软键盘
                    break;

                case R.id.edt_pwd: // 输入密码时弹出键盘
                    EditTextUserPsw.setFocusable(true);
                    EditTextUserPsw.setFocusableInTouchMode(true);
                    EditTextUserPsw.requestFocus();
                    EditTextUserPsw.findFocus();
                    InputMethodManager imm2 = (InputMethodManager) RegistActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm2.showSoftInput(EditTextUserPsw, InputMethodManager.SHOW_FORCED);// 显示软键盘

            }
        }
    }

    private boolean editTextIsEmpty() {
        /**
         * @Author SYT
         * @Description 判断输入框用户名和密码是否为空
         * @Date 21:39 2019/6/10
         * @Param * @param
         * @return boolean
         **/

        return  EditTextUserPsw.getText().toString()!=null  &&  EditTextUserName.getText().toString()!=null;

    }


}
