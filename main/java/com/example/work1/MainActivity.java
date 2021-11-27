package com.example.work1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {
      private List<Msg> msgList=new ArrayList<>();
      private EditText inputText;
      private Button send;
      private RecyclerView msgRecyclerView;
      private MsgAdapter adapter;

              @Override
      protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_main);
                 initMsgs();                                                         //初始化消息数据
                 inputText=(EditText)findViewById(R.id.input_text);
                 send=(Button)findViewById(R.id.send);
                 msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);

                 LinearLayoutManager layoutManager=new LinearLayoutManager(this);    //LinearLayoutLayout即线性布局，创建对象后把它设置到RecyclerView当中
                 msgRecyclerView.setLayoutManager(layoutManager);

                 adapter=new MsgAdapter(msgList);                                    //创建MsgAdapter的实例并将数据传入到MsgAdapter的构造函数中
                 msgRecyclerView.setAdapter(adapter);

                 send.setOnClickListener(new View.OnClickListener(){                 //发送按钮点击事件
             @Override
             public void onClick(View v){
                                 String content=inputText.getText().toString();              //获取EditText中的内容
                                if(!"".equals(content)){                                    //内容不为空则创建一个新的Msg对象，并把它添加到msgList列表中
                                         Msg msg=new Msg(content,Msg.TYPE_SENT);
                                         msgList.add(msg);
                                         adapter.notifyItemInserted(msgList.size()-1);           //调用适配器的notifyItemInserted()用于通知列表有新的数据插入，这样新增的一条消息才能在RecyclerView中显示31                     msgRecyclerView.scrollToPosition(msgList.size()-1);     //调用scrollToPosition()方法将显示的数据定位到最后一行，以保证可以看到最后发出的一条消息
                                         inputText.setText("");                                  //调用EditText的setText()方法将输入的内容清空
                                     }
                             }
         });
             }

             private void initMsgs(){
                 Msg msg1=new Msg("你好",Msg.TYPE_RECEIVED);
                 msgList.add(msg1);
                 Msg msg2=new Msg("你好",Msg.TYPE_SENT);
                 msgList.add(msg2);
                 Msg msg3=new Msg("你最近好吗？",Msg.TYPE_RECEIVED);
                 msgList.add(msg3);
            }
}