package mo.com.democollection.ui;/**
 * Created by  on
 */

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import mo.com.democollection.R;

/**
 * @创建者 MoMxMo
 * @创时间 2015/10/23:10:19
 * @描述 TODO
 * @项目名 DemoCollection
 * @版本 $Rev
 * @更新者 $Author
 * @更新时间 $Date
 * @更新描述 TODO
 */
public class SenSorUI extends AppCompatActivity {

    private TextView mTvOritationResult;
    private  SensorManager manager;
    private MyOritationListener myOritationListener;
    private MyRightListener myrightListener;
    private TextView mTvLightResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        mTvOritationResult = (TextView) findViewById(R.id.oritation_sersor_result);
        mTvLightResult = (TextView) findViewById(R.id.light_sersor_result);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    /**
     * 开启光传感器
     * @param view
     */
    public void openLight(View view) {
        //1、获取到光传感器对象
        Sensor right_sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //2、注册光感应，监听光传感器：
        // 第一个参数是：监听器
        // 第二个参数是：监听那一个类的传感器
        // 第三个参数是：监听的速率

        //创建监听器

        myrightListener = new MyRightListener();

        manager.registerListener(myrightListener,right_sensor,SensorManager.SENSOR_DELAY_NORMAL);
//当界面销毁的时候，取消注册的传感器的监听
        manager.unregisterListener(myOritationListener);
    }
    /**
     * 开启指南针传感器
     * @param view
     */
    public void openOritation(View view) {

        //1.获取方向传感器对象
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //2、注册光感应，监听光传感器：
        // 第一个参数是：监听器
        // 第二个参数是：监听那一个类的传感器
        // 第三个参数是：监听的速率
        //创建监听器
        myOritationListener = new MyOritationListener();
        manager.registerListener(myOritationListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    /**
     * 指南针传感器的监听器
     */
    class MyOritationListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //values[0]:代表是当前手机的Y轴和正北方向的夹角 0=North, 90=East, 180=South, 270=West
            int value = (int) event.values[0];
            String str = "";
            if (value == 0) {
                str = "现在的位置是：正北------>" + value;
            } else if (value == 90) {
                str = "现在的位置是：正东------>" + value;
            } else if (value == 180) {
                str = "现在的位置是：正南------>" + value;
            } else if (value == 270) {
                str = "现在的位置是：正西------>" + value;
            } else if (value > 0 && value < 90) {
                str = "现在的位置是：东北------>" + value;
            } else if (value > 90 && value < 180) {
                str = "现在的位置是：东南------>" + value;
            } else if (value > 180 && value < 270) {
                str = "现在的位置是：西南------>" + value;
            } else if (value > 270 && value < 360) {
                str = "现在的位置是：西北------>" + value;
            }
            mTvOritationResult.setText(str);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    }

    class MyRightListener implements SensorEventListener {    //创建一个传感器的监听器

        //数据发送改变的时候调用
        @Override
        public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            mTvLightResult.setText("光强度为"+value );
        }

        //精度发生改变的时候调用
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    protected void onDestroy() {
        //退出的时候取消注册的传感器
        manager.unregisterListener(myOritationListener);
        manager.unregisterListener(myrightListener);
        super.onDestroy();
    }
}
