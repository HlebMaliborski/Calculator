package com.example.hmaliborski.calculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = MainActivity.class.getSimpleName();
    private int[] firstButtonId;
    private int[] firstButtonText;
    private static final int MATCH_PARENT = -1;
    private static final int WRAP_CONTENT = -2;

    private ArrayList<View> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linLayoutParam = createParams(MATCH_PARENT, MATCH_PARENT, 0.0f);
        linLayout.setLayoutParams(linLayoutParam);

        ViewGroup topView = createTopView();
        topView = addViewsToRoot(topView);

        ViewGroup bottomView = createBottomView();

        linLayout.addView(topView);
        linLayout.addView(bottomView);
        setContentView(linLayout);
    }


    private ViewGroup createTopView() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        LinearLayout.LayoutParams relativeParams = createParams(MATCH_PARENT, MATCH_PARENT, 3.0f);
        relativeLayout.setBackgroundColor(Color.parseColor("#fffefe"));
        relativeLayout.setLayoutParams(relativeParams);

        TextView tvResult = new TextView(this);
        tvResult.setText(R.string.default_value);
        tvResult.setId(R.id.result);
        tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50f);
        RelativeLayout.LayoutParams tvResultParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        tvResultParams.bottomMargin = 50;
        tvResultParams.rightMargin = 20;
        tvResultParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        tvResultParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tvResult.setLayoutParams(tvResultParams);


        TextView tvInputText = new TextView(this);
        tvInputText.setText(R.string.default_value);
        tvInputText.setId(R.id.input_text);
        tvInputText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
        RelativeLayout.LayoutParams tvInputTextParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        tvInputTextParams.bottomMargin = 20;
        tvInputTextParams.rightMargin = 20;
        tvInputTextParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        tvInputTextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tvInputText.setLayoutParams(tvInputTextParams);

        views.add(tvResult);
        views.add(tvInputText);

        return relativeLayout;
    }

    private ViewGroup createBottomView() {
        ViewGroup rootView = createRootViewForBottom();
        firstButtonId = Constants.FIRST_BUTTON_ID;
        firstButtonText = Constants.FIRST_BUTTON_NAME;
        View firstChildView = createRow();
        firstButtonId = Constants.SECOND_BUTTON_ID;
        firstButtonText = Constants.SECOND_BUTTON_NAME;
        View secondChildView = createRow();
        firstButtonId = Constants.THIRD_BUTTON_ID;
        firstButtonText = Constants.THIRD_BUTTON_NAME;
        View thirdChildView = createRow();

        View combineView = createColumn();

        rootView.addView(firstChildView);
        rootView.addView(secondChildView);
        rootView.addView(thirdChildView);
        rootView.addView(combineView);
        return rootView;
    }

    private ViewGroup createRootViewForBottom() {
        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        linLayout.setBackgroundColor(Color.parseColor("#dcbdbd"));
        LinearLayout.LayoutParams linLayoutParam = createParams(MATCH_PARENT, MATCH_PARENT, 2.0f);
        linLayout.setLayoutParams(linLayoutParam);
        return linLayout;
    }

    private View createRow() {
        ViewGroup rootView = createRootForRow();

        for (int i = 0; i < 4; i++) {
            createButton(i);
        }
        rootView = addViewsToRoot(rootView);
        return rootView;
    }

    private ViewGroup createRootForRow() {
        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams linLayoutParam = createParams(MATCH_PARENT, MATCH_PARENT, 4.0f);
        linLayoutParam.topMargin = 1;
        linLayout.setLayoutParams(linLayoutParam);
        return linLayout;
    }

    private void createButton(int position) {
        ContextThemeWrapper themeContext = new ContextThemeWrapper(this, R.style.button_combine_style_programmatically);
        TextView tvInputText = new TextView(themeContext);
        tvInputText.setText(firstButtonText[position]);
        tvInputText.setId(firstButtonId[position]);
        LinearLayout.LayoutParams params = createParams(MATCH_PARENT, MATCH_PARENT, 1.0f);
        if (position < 3)
            params.rightMargin = 1;
        tvInputText.setLayoutParams(params);

        views.add(tvInputText);
    }


    private View createColumn() {
        ViewGroup viewGroup = createRootForCombine();
        firstButtonId = Constants.FIRST_COLUMN;
        firstButtonText = Constants.FIRST_NAME;
        View firstRootColumn = createRootForColumn();
        firstButtonId = Constants.SECOND_COLUMN;
        firstButtonText = Constants.SECOND_NAME;
        View secondRootColumn = createRootForColumn();
        firstButtonId = Constants.THIRD_COLUMN;
        firstButtonText = Constants.THIRD_NAME;
        View thirdRootColumn = createRootForColumn();
        View resultView = createResultView();

        viewGroup.addView(firstRootColumn);
        viewGroup.addView(secondRootColumn);
        viewGroup.addView(thirdRootColumn);
        viewGroup.addView(resultView);

        return viewGroup;
    }

    private ViewGroup createRootForCombine() {
        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams linLayoutParam = createParams(MATCH_PARENT, MATCH_PARENT, 3.0f);
        linLayoutParam.topMargin = 1;
        linLayout.setLayoutParams(linLayoutParam);

        return linLayout;
    }

    private ViewGroup createRootForColumn() {
        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linLayoutParam = createParams(0, MATCH_PARENT, 1.0f);
        linLayout.setLayoutParams(linLayoutParam);

        for (int i = 0; i < 2; i++) {
            createCombineButton(i);
        }

        return addViewsToRoot(linLayout);
    }

    private void createCombineButton(int position) {
        ContextThemeWrapper themeContext = new ContextThemeWrapper(this, R.style.button_combine_style_programmatically);
        TextView tvInputText = new TextView(themeContext);
        tvInputText.setText(firstButtonText[position]);
        tvInputText.setId(firstButtonId[position]);
        LinearLayout.LayoutParams params = createParams(MATCH_PARENT, MATCH_PARENT, 1.0f);
        if (position == 1)
            params.topMargin = 1;
        params.rightMargin = 1;
        tvInputText.setLayoutParams(params);

        views.add(tvInputText);
    }

    private View createResultView() {
        ContextThemeWrapper themeContext = new ContextThemeWrapper(this, R.style.button_result);
        TextView tvInputText = new TextView(themeContext);
        tvInputText.setText(R.string.btn_equally);
        tvInputText.setId(R.id.btn_equally);
        LinearLayout.LayoutParams params = createParams(0, MATCH_PARENT, 1.0f);
        tvInputText.setLayoutParams(params);

        return tvInputText;
    }

    private ViewGroup addViewsToRoot(ViewGroup viewGroup) {
        for (int i = 0; i < views.size(); i++) {
            viewGroup.addView(views.get(i));
        }

        views.clear();
        return viewGroup;
    }

    public LinearLayout.LayoutParams createParams(int width, int height, float weight) {
        if (weight > 0)
            return new LinearLayout.LayoutParams(width, height, weight);
        else
            return new LinearLayout.LayoutParams(width, height);
    }
}
