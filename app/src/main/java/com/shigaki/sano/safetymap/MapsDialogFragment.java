package com.shigaki.sano.safetymap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.DialogFragment;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
//DialogFragmentのサポートクラスをインポート

public class MapsDialogFragment extends DialogFragment
{

    public static MapsDialogFragment newInstance(String marker_title, String marker_exp,int marker_collect,int marker_wrong)
    {
        MapsDialogFragment frag = new MapsDialogFragment();
        Bundle args = new Bundle();
        args.putString("title",marker_title);
        args.putString("explanation",marker_exp);
        args.putInt("total_collect",marker_collect);
        args.putInt("total_wrong",marker_wrong);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final View dialogView = LayoutInflater.from(activity).inflate(R.layout.fragment_dialog, null);

        //Viewの読み込み
        TextView marker_title_view = dialogView.findViewById(R.id.marker_title);
        TextView marker_explanation_view = dialogView.findViewById(R.id.marker_explanation);
        TextView total_collect_view = dialogView.findViewById(R.id.total_collect);
        TextView total_wrong_view = dialogView.findViewById(R.id.total_wrong);

        //ボタンの読み込み
        Button button_collect = dialogView.findViewById(R.id.button_collect);
        Button button_wrong = dialogView.findViewById(R.id.button_wrong);

        //ボタンリスナーセット
        button_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"ご協力ありがとうございます！",Toast.LENGTH_SHORT).show();
            }
        });
        button_wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"ご協力ありがとうございます！！",Toast.LENGTH_SHORT).show();
            }
        });

        //Viewに文字列をセット
        marker_title_view.setText(getArguments().getString("title"));
        marker_explanation_view.setText(getArguments().getString("explanation"));
        total_collect_view.setText(String.valueOf(getArguments().getInt("total_collect"))+"件");
        total_wrong_view.setText(String.valueOf(getArguments().getInt("total_wrong"))+"件");

        //閉じるボタン実装
        builder.setView(dialogView)
                .setNegativeButton("閉じる", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        MapsDialogFragment.this.dismiss();
                    }
                });

        return builder.create();

    }


}