package org.tomislavgazica.newsapp.ui.errorDialog;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tomislavgazica.newsapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomErrorDialogFragment extends DialogFragment {

    private OnCustomDialogFragmentClickListener listener;

    public void setListener(OnCustomDialogFragmentClickListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.error_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.dialogTryAgain)
    public void tryAgain(){
        listener.tryAgain();
        dismiss();
        onDestroy();
    }

}
