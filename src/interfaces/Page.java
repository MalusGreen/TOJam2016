package interfaces;

import java.util.HashMap;

/**
 * Created by Kevin Zheng on 2016-05-02.
 */
public interface Page extends Updatable{
    HashMap<String, Page> pages = new HashMap<String, Page>();

    void toPage();
}
