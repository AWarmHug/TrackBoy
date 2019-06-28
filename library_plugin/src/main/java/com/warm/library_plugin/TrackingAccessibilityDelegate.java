package com.warm.library_plugin;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CompoundButton;

import com.warm.library_plugin.helper.ViewActionHelper;

public class TrackingAccessibilityDelegate extends View.AccessibilityDelegate {

    @Override
    public void sendAccessibilityEvent(View host, int eventType) {
        super.sendAccessibilityEvent(host, eventType);
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                if (host instanceof CompoundButton) {
                    ViewActionHelper.setChecked(host, ((CompoundButton) host).isChecked());
                } else {
                    ViewActionHelper.performClick(host);
                }
                break;
        }
    }

    @Override
    public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
        super.sendAccessibilityEventUnchecked(host, event);
    }


}
