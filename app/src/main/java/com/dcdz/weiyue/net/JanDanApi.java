package com.dcdz.weiyue.net;

import android.support.annotation.StringDef;
import com.dcdz.weiyue.been.FreshNewsArticleBean;
import com.dcdz.weiyue.been.FreshNewsBean;
import com.dcdz.weiyue.been.JdDetailBean;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.reactivex.Observable;

public class JanDanApi {

    public static final String TYPE_FRESH = "get_recent_posts"; //煎蛋——新鲜事
    public static final String TYPE_FRESHARTICLE = "get_post"; //煎蛋——新鲜事——文章
    public static final String TYPE_BORED = "jandan.get_pic_comments"; //煎蛋——无聊图
    public static final String TYPE_GIRLS = "jandan.get_ooxx_comments"; //煎蛋——妹子图
    public static final String TYPE_Duan = "jandan.get_duan_comments"; //煎蛋——段子

    @StringDef({TYPE_FRESH, TYPE_BORED, TYPE_GIRLS, TYPE_Duan})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {

    }

    public static JanDanApi sInstance;

    private JanDanApiService mService;

    public JanDanApi(JanDanApiService janDanApiService) {
        this.mService = janDanApiService;
    }

    public static JanDanApi getInstance(JanDanApiService janDanApiService) {
        if (sInstance == null)
            sInstance = new JanDanApi(janDanApiService);
        return sInstance;
    }

    /**
     * 获取新鲜事列表
     *
     * @param page 页码
     * @return
     */
    public Observable<FreshNewsBean> getFreshNews(int page) {
        return mService.getFreshNews(ApiConstants.sJanDanApi, TYPE_FRESH,
                "url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields",
                page, "thumb_c,views", "1");
    }

    /**
     * 获取 无聊图，妹子图，段子列表
     *
     * @param type {@link Type}
     * @param page 页码
     * @return
     */
    public Observable<JdDetailBean> getJdDetails(@Type String type, int page) {
        return mService.getDetailData(ApiConstants.sJanDanApi, type, page);
    }

    /**
     * 获取新鲜事文章详情
     *
     * @param id PostsBean id {@link FreshNewsBean.PostsBean}
     * @return
     */
    public Observable<FreshNewsArticleBean> getFreshNewsArticle(int id) {
        return mService.getFreshNewsArticle(ApiConstants.sJanDanApi, TYPE_FRESHARTICLE, "content,date,modified", id);
    }

}
