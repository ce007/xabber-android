package com.xabber.android.ui.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.xabber.androiddev.R;

public class RoomAddDialogBuilder extends ConfirmDialogBuilder {

    private final EditText roomNameView;

    public RoomAddDialogBuilder(Activity activity, int dialogId,
                                 ConfirmDialogListener listener) {
        super(activity, dialogId, listener);
        setTitle(R.string.add_room_uuid);

        View layout = activity.getLayoutInflater().inflate(R.layout.room_name,
                null);
        roomNameView = (EditText) layout.findViewById(R.id.room_name);
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
}
