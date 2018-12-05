package com.jscheng.spluto;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.jscheng.spluto.dao.MarkdownTextDao;
import com.jscheng.spluto.dao.MarkdownTextInfo;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MarkdownTextSqlTest {
    @Test
    public void test() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        MarkdownTextDao textDao = new MarkdownTextDao(appContext);
        MarkdownTextInfo info1 = new MarkdownTextInfo("name", "path", false);
        MarkdownTextInfo info2 = new MarkdownTextInfo("test", "path2", true);
        MarkdownTextInfo info3 = new MarkdownTextInfo("test3", "path3", true);
        textDao.add(info1);
        textDao.add(info2);
        textDao.add(info3);
        int textCount = textDao.getCount();
        assertEquals(textCount, 3);

        for (MarkdownTextInfo info : textDao.getOffetsData(0, textCount)) {
            Log.v("CJS", "id : " + info.getId() +
                    " name: " + info.getName() +
                    " path: " + info.getPath() +
                    " summary: " + info.getSummary() +
                    " exist: " + info.isExist() );
            info.setSummary("change");
            textDao.update(info);
            assertEquals(textDao.find(info.getId()).getSummary(), info.getSummary());
            textDao.delete(info.getId());
        }
        assertEquals(textDao.getCount(), 0);
    }
}
