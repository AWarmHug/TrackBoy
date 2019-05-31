package com.warm.someaop;

import android.view.View;

public class TrackView {


    public static void bind(View view, String eventId, String... events) {
        Event event1 = new Event(eventId, events);
        view.setTag(R.id.key_track, event1);
    }

    public static class Event {
        String eventId;
        String[] events;

        public String getEventId() {
            return eventId;
        }

        public String[] getEvents() {
            return events;
        }

        public Event(String eventId, String[] events) {
            this.eventId = eventId;
            this.events = events;
        }

    }

}
