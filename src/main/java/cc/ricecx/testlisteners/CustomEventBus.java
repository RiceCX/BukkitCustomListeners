package cc.ricecx.testlisteners;

import cc.ricecx.testlisteners.annotation.WorldEventHandler;
import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import lombok.SneakyThrows;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomEventBus {

    public Map<Class<? extends Event>, Map.Entry<Object, Method>> events = new HashMap<>();

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void register(Listener... listeners) {

        for (Listener listener : listeners) {
            for (Method method : listener.getClass().getMethods()) {
                if (method.isAnnotationPresent(WorldEventHandler.class)) {
                    Parameter parameter = method.getParameters()[0];
                    var name = parameter.getParameterizedType().getTypeName();

                    Pattern pattern = Pattern.compile("<(\\w.*)>");
                    Matcher matcher = pattern.matcher(name);

                    String type;
                    if (matcher.find()) type = matcher.group(1);
                    else continue;

                    var clazz = Class.forName(type);
                    events.put((Class<? extends Event>) clazz, Map.entry(listener, method));
                }
            }
        }
    }

    public void callEvent(AbstractWorldEvent<?> event) {
        Map.Entry<Object, Method> method = events.get(event.getBukkitEvent().getClass());
        if (method != null) {
            try {
                method.getValue().invoke(method.getKey(), event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
