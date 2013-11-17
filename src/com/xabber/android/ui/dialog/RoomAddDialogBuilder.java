package com.xabber.android.ui.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.xabber.android.data.account.AccountManager;
import com.xabber.android.ui.adapter.AccountChooseAdapter;
import com.xabber.androiddev.R;

import java.util.Collection;

public class RoomAddDialogBuilder extends ConfirmDialogBuilder {

    private final EditText roomNameView;
    private final EditText serverNameView;
    private final Spinner accountView;

    public RoomAddDialogBuilder(Activity activity, int dialogId,
                                 ConfirmDialogListener listener) {
        super(activity, dialogId, listener);
        setTitle(R.string.add_room_uuid);

        View layout = activity.getLayoutInflater().inflate(R.layout.room_name,
                null);
        roomNameView = (EditText) layout.findViewById(R.id.room_name);
        serverNameView = (EditText) layout.findViewById(R.id.addroom_server);
        accountView = (Spinner) layout.findViewById(R.id.addroom_account);
        accountView.setAdapter(new AccountChooseAdapter(activity));
        Collection<String> accounts = AccountManager.getInstance()
                   .getAccounts();
        String account = "";

        if (accounts.size() == 1)
             account = accounts.iterator().next();

        for (int position = 0; position < accountView.getCount(); position++)
            if (account.equals(accountView.getItemAtPosition(position))) {
                    accountView.setSelection(position);
                    break;
            }
        setView(layout);
    }

    @Override
    public void onAccept(DialogInterface dialog) {
        String name = roomNameView.getText().toString();
        if ("".equals(name)) {
            Toast.makeText(activity,
                    activity.getString(R.string.room_name_is_empty),
                    Toast.LENGTH_LONG).show();
            return;
        }
        super.onAccept(dialog);
    }

    public String getRoomName() {
        return roomNameView.getText().toString();
    }

    public String getServer() {
        return  serverNameView.getText().toString();
    }

    public String getAccount() {
        return (String) accountView.getSelectedItem();
    }
}
