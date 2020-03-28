package com.example.liv_ez;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_CREATED = "createdAt";
    public static final String KEY_USER = "user";
    public static final String KEY_CHECKED = "Checked";
    public static final String KEY_ASSIGNED = "assignedTo";
    public static final String KEY_OBJECTID = "objectId";

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public java.util.Date getDateCreated() {
        return getDate(KEY_CREATED);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public boolean getChecked() {
        return getBoolean(KEY_CHECKED);
    }

    public void setChecked(boolean Checked) {
        put(KEY_CHECKED, Checked);
    }

    public ParseUser getAssigned() {
        return getParseUser(KEY_ASSIGNED);
    }

    public boolean getIfAssigned() {

        if (getParseUser(KEY_ASSIGNED) != null) {
            return true;
        }
        return false;
    }

    public void setAssigned(ParseUser assignedTo) {
        put(KEY_ASSIGNED, assignedTo);
    }

    public String getObjectId() {
        return getString(KEY_OBJECT_ID);
    }

}
