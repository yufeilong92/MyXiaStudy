package com.lawyee.appservice.config;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.lawyee.appservice.R;
import com.lawyee.appservice.vo.AreaVO;
import com.lawyee.appservice.vo.DataDictionaryVO;
import com.lawyee.appservice.vo.PublicUserVO;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.lawyee.mobilelib.json.JsonParser;
import net.lawyee.mobilelib.utils.FileUtil;
import net.lawyee.mobilelib.utils.StringUtil;
import net.lawyee.mobilelib.vo.ResponseVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局变量
 * Created by wuzhu on 2016/3/1.
 */
public class ApplicationSet extends Application {


    private static Context mContext;
    /**
     * 公众用户登录信息
     */
    private PublicUserVO mUserVO;

    /**
     * 数据字典
     */
    private List<DataDictionaryVO> mDataDictionarys;

    /**
     * 区域数据
     */
    private List<AreaVO> mAreas;

    private List<Activity> mActivities = new ArrayList<Activity>();

    private static ApplicationSet mApplication;

    public synchronized static ApplicationSet getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        mApplication = this;
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
        mMainLooper = getMainLooper();
        mHandler = new Handler();
        initImageLoader(mContext);
    }


    static {
        setGlobalParams();
    }

    static void setGlobalParams()
    {
        JsonParser.PACKAGE_NAME = "com.lawyee.appservice.vo.";
        Constants.DataFileDir = "appservice";

    }
    public static Context getmContext() {
        return mContext;
    }
    public PublicUserVO getUserVO() {
        return mUserVO;
    }



    public static DisplayImageOptions CDIO_DEFAULT = new DisplayImageOptions.Builder()//
            .showImageOnLoading(R.mipmap.icon_image_def_min)         //设置图片在下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.icon_image_def_min)       //设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.icon_image_def_min)            //设置图片加载/解码过程中错误时候显示的图片
            .cacheInMemory(true)                                //设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)                                  //设置下载的图片是否缓存在SD卡中
            .build();                                           //构建完成

    /**
     * ImageLoader 图片组件初始化
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        String caseDir = net.lawyee.mobilelib.Constants.getDataStoreDir(context) + net.lawyee.mobilelib.Constants.CSTR_IMAGECACHEDIR;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                //.memoryCacheSize(2 * 1024 * 1024)
                .diskCache(new UnlimitedDiskCache(new File(caseDir)))//自定义缓存路径
                .defaultDisplayImageOptions(CDIO_DEFAULT)
                //.writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }
    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列
    private static Handler mHandler;//主线程Handler


    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        ApplicationSet.mMainThread = mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        ApplicationSet.mMainThreadId = mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainLooper(Looper mMainLooper) {
        ApplicationSet.mMainLooper = mMainLooper;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void setMainHandler(Handler mHandler) {
        ApplicationSet.mHandler = mHandler;
    }
    /**
     * 设置公众用户登录信息
     * @param userVO 如果注销，请传入null清除数据
     * @param save 是否保存
     */
    public void setUserVO(PublicUserVO userVO,Boolean save) {
        this.mUserVO = userVO;
        if(save)
            PublicUserVO.saveVO(userVO==null?new PublicUserVO():userVO,PublicUserVO.dataFileName(getApplicationContext()));
    }

    /**
     * 获取数据字典
     * @param autoinit 是否自动初始化
     * @return
     */
    public List<DataDictionaryVO> getDataDictionarys(boolean autoinit) {
        if(autoinit)
            return getDataDictionarys();
        return mDataDictionarys;
    }

    /**
     * 获取数据字典
     * @return 数据为空时读取默认数据
     */
    public List<DataDictionaryVO> getDataDictionarys() {
        if(mDataDictionarys==null)
            initDataDictionary();
        return mDataDictionarys;
    }

    public void setDataDictionarys(List<DataDictionaryVO> dataDictionarys) {
        this.mDataDictionarys = dataDictionarys;
    }

    /**
     * 获取区域信息
     * @param autoinit 是否自动初始化
     * @return
     */
    public List<AreaVO> getAreas(boolean autoinit) {
        if(autoinit)
            return getAreas();
        return mAreas;
    }
    /**
     * 获取区域信息
     * @return 数据为空时读取默认数据
     */
    public List<AreaVO> getAreas() {
        if(mAreas==null)
            initAreas();
        return mAreas;
    }

    public void setAreas(List<AreaVO> areas) {
        this.mAreas = areas;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (mActivities != null && mActivities.size() > 0) {
            if(!mActivities.contains(activity)){
                mActivities.add(activity);
            }
        }else{
            mActivities.add(activity);
        }

    }

    public void removeActivity(Activity activity) {
        if (mActivities != null && mActivities.size() > 0) {
            if(mActivities.contains(activity)){
                mActivities.remove(activity);
            }
        }
    }

    // 遍历所有Activity并finish
    public void finishAllActivity() {
        if (mActivities != null && mActivities.size() > 0) {
            for (Activity activity : mActivities) {
                activity.finish();
            }
        }
    }


    /**
     * 缺省方式初始化区域表
     */
    public static void initAreas()
    {
        //本地读取区域信息
        String areas = FileUtil.readFileFromAssetsFile(mContext,"Areas.json");
        List<AreaVO> areaVOs = null;
        if(!StringUtil.isEmpty(areas)) {
            areaVOs = (List<AreaVO>) JsonParser.parseJsonToList(areas,new ResponseVO());
        }
        if(areaVOs==null)
            areaVOs = new ArrayList<AreaVO>();
        ApplicationSet.getInstance().setAreas(areaVOs);
    }

    /**
     * 缺省方式初始化数据字典
     */
    public static void initDataDictionary()
    {
        //先读取缓存
        List dataDictionaryVOs = DataDictionaryVO.loadVOList(DataDictionaryVO.dataListFileName(mContext));
        if(dataDictionaryVOs==null||dataDictionaryVOs.isEmpty())
        {
            //缓存为空，就读取默认的
            String dataDictionarys = FileUtil.readFileFromAssetsFile(mContext, "DataDictionary.json");
            if (!StringUtil.isEmpty(dataDictionarys)) {
                dataDictionaryVOs = JsonParser.parseJsonToList(dataDictionarys, new ResponseVO());
            }
        }
        if(dataDictionaryVOs==null)
            dataDictionaryVOs = new ArrayList();
        ApplicationSet.getInstance().setDataDictionarys(dataDictionaryVOs);
    }
}
