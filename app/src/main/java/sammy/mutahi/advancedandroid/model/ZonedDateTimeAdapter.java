package sammy.mutahi.advancedandroid.model;

import androidx.annotation.Nullable;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.ZonedDateTime;

public class ZonedDateTimeAdapter {
    @FromJson
    ZonedDateTime fromJson(String json){
        return ZonedDateTime.parse(json);
    }

    @ToJson
    String toJson(@Nullable ZonedDateTime zonedDateTime){
        return zonedDateTime != null ? zonedDateTime.toString() : null;
    }
}
