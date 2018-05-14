package com.huitu.alyssachia.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huitu.alyssachia.Interface.OnItemClickListener;
import com.huitu.alyssachia.R;
import com.huitu.alyssachia.base.BaseFragment;
import com.huitu.alyssachia.ui.activity.News2Activity;
import com.huitu.alyssachia.ui.activity.NewsActivity;
import com.huitu.alyssachia.ui.activity.UploadHeadActivity;
import com.huitu.alyssachia.ui.activity.UploadImagesActivity;

import java.util.ArrayList;


/**
 * Created by l on 2017/7/21.
 */

public class CategoryFragment extends BaseFragment implements OnItemClickListener {

    private ArrayList mDatas;
    private TextView tvTitle;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    public static CategoryFragment newInstance() {
        Bundle bundle = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_category;
    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void initView(View root) {
        initData();
        tvTitle = (TextView) root.findViewById(R.id.tv_title);
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        tvTitle.setText("Category");
        CategoryAdapter adapter = new CategoryAdapter();
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.HORIZONTAL));
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void setListeners() {
    }

    @Override
    public void bind() {

    }

    @Override
    public void unBind() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        mDatas.add("UploadHead");
        mDatas.add("UploadImages");
        mDatas.add("TopNews");
        mDatas.add("...");
        mDatas.add("...");
        mDatas.add("...");
        mDatas.add("...");
    }

    @Override
    public void onItemClick(View view) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        switch (childAdapterPosition){
            case 0://上传头像
                startActivity(new Intent(getActivity(), UploadHeadActivity.class));
                break;
            case 1://上传多张相片
                startActivity(new Intent(getActivity(), UploadImagesActivity.class));
                break;
            case 2:
                startActivity(new Intent(getActivity(), NewsActivity.class));
                break;
            case 3:
                startActivity(new Intent(getActivity(), News2Activity.class));
                break;
            default:
                break;
        }
    }

    class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>
    {

        OnItemClickListener mOnItemClickListener;
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_category, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(mDatas.get(position).toString());
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v);
                }
            }
        }
    }
}
