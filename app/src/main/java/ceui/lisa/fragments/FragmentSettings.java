package ceui.lisa.fragments;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringChain;

import java.util.List;

import ceui.lisa.R;
import ceui.lisa.activities.LoginActivity;
import ceui.lisa.activities.LoginAlphaActivity;
import ceui.lisa.activities.Shaft;
import ceui.lisa.activities.TemplateFragmentActivity;
import ceui.lisa.utils.Common;
import ceui.lisa.utils.Local;

public class FragmentSettings extends BaseFragment {

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_settings;
    }

    @Override
    View initView(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> getActivity().finish());
        LinearLayout linearLayout = v.findViewById(R.id.parent_linear);
        animate(linearLayout);

        RelativeLayout loginOut = v.findViewById(R.id.login_out);
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginAlphaActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        RelativeLayout userManage = v.findViewById(R.id.user_manage);
        userManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
                intent.putExtra(TemplateFragmentActivity.EXTRA_FRAGMENT, "账号管理");
                startActivity(intent);
                getActivity().finish();
            }
        });

        Switch staggerAnime = v.findViewById(R.id.stagger_animate);
        staggerAnime.setChecked(Shaft.sSettings.isStaggerAnime());
        staggerAnime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Shaft.sSettings.setStaggerAnime(true);
                }else {
                    Shaft.sSettings.setStaggerAnime(false);
                }
                Local.setSettings(Shaft.sSettings);
            }
        });

        Switch gridAnime = v.findViewById(R.id.grid_animate);
        gridAnime.setChecked(Shaft.sSettings.isGridAnime());
        gridAnime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Shaft.sSettings.setGridAnime(true);
                }else {
                    Shaft.sSettings.setGridAnime(false);
                }
                Local.setSettings(Shaft.sSettings);
            }
        });


        Switch saveHistory = v.findViewById(R.id.save_history);
        saveHistory.setChecked(Shaft.sSettings.isSaveViewHistory());
        saveHistory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Shaft.sSettings.setSaveViewHistory(true);
                }else {
                    Shaft.sSettings.setSaveViewHistory(false);
                }
                Local.setSettings(Shaft.sSettings);
            }
        });

        Switch relatedNoLimit = v.findViewById(R.id.related_no_limit);
        relatedNoLimit.setChecked(Shaft.sSettings.isRelatedIllustNoLimit());
        relatedNoLimit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Shaft.sSettings.setRelatedIllustNoLimit(true);
                }else {
                    Shaft.sSettings.setRelatedIllustNoLimit(false);
                }
                Local.setSettings(Shaft.sSettings);
            }
        });


        Switch autoDns = v.findViewById(R.id.auto_dns);
        autoDns.setChecked(Shaft.sSettings.isAutoFuckChina());
        autoDns.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Shaft.sSettings.setAutoFuckChina(true);
                }else {
                    Shaft.sSettings.setAutoFuckChina(false);
                }
                Local.setSettings(Shaft.sSettings);
            }
        });

        return v;
    }

    @Override
    void initData() {

    }

    private void animate(LinearLayout linearLayout){
        SpringChain springChain = SpringChain.create(40,8,60,10);

        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = linearLayout.getChildAt(i);

            final int position = i;
            springChain.addSpring(new SimpleSpringListener() {
                @Override
                public void onSpringUpdate(Spring spring) {
                    view.setTranslationX((float) spring.getCurrentValue());
                    //view.setAlpha((float) ((400 - spring.getCurrentValue()) / 400 ) );
                    if(position == 0){
                        Common.showLog(className + (float) spring.getCurrentValue());
                    }
                }
            });
        }

        List<Spring> springs = springChain.getAllSprings();
        for (int i = 0; i < springs.size(); i++) {
            springs.get(i).setCurrentValue(400);
        }
        springChain.setControlSpringIndex(0).getControlSpring().setEndValue(0);
    }
}
