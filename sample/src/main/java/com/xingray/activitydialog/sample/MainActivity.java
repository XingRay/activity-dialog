package com.xingray.activitydialog.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    private static final List<Function> functions = Arrays.asList(
            new Function("activity-dialog test", ActivityDialogTestActivity.class)
    );

    private List<Function> mFunctions;
    private Activity mActivity;
    private Context mContext;
    private FunctionAdapter mFunctionAdapter;
    private RecyclerView rvList;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initView();
        loadData();
    }

    protected void initVariables() {
        mActivity = this;
        mContext = this.getApplicationContext();
        mFunctions = new ArrayList<>();
        mFunctionAdapter = new FunctionAdapter(rvList, mContext, mFunctions);
    }

    protected void initView() {
        setContentView(R.layout.activity_main);

        rvList = (RecyclerView) findViewById(R.id.rv_list);
        rvList.setAdapter(mFunctionAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        mFunctionAdapter.setOnItemClickListener(this);
    }

    protected void loadData() {
        mFunctions.addAll(functions);
        mFunctionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View parent, View view, int position, int id) {
        Function function = mFunctions.get(id);
        if (function == null) {
            Toast.makeText(mContext, "item is null, id:" + id, Toast.LENGTH_SHORT).show();
            return;
        }
        function.exec(this);
    }


    private static class FunctionAdapter extends RecyclerView.Adapter<FunctionViewHolder> {

        private final Context mContext;
        private final List<Function> mFunctions;
        private final LayoutInflater mInflater;
        private OnItemClickListener mOnItemClickListener;
        private final View mParent;

        private FunctionAdapter(View parent, Context context, List<Function> functions) {
            mParent = parent;
            mContext = context;
            mFunctions = functions;
            mInflater = LayoutInflater.from(mContext);
        }

        private void setOnItemClickListener(OnItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        @Override
        public FunctionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.list_item_main_page_item, parent, false);
            return new FunctionViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final FunctionViewHolder holder, int position) {
            holder.tvText.setText(mFunctions.get(position).getName());
            holder.tvText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(mParent, holder.tvText, holder.getAdapterPosition(), holder.getLayoutPosition());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mFunctions.size();
        }
    }

    @SuppressWarnings("RedundantCast")
    private static class FunctionViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvText;

        private FunctionViewHolder(View itemView) {
            super(itemView);

            tvText = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
