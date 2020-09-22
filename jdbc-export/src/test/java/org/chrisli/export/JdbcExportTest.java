package org.chrisli.export;

import org.chrisli.export.service.ExportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * [数据库导出]
 *
 * @author Administrator[黎超]
 * @create [2017-04-12]
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcExportTest {

    @Autowired
    private ExportService exportService;

    @Test
    public void export() {
        exportService.exportTableColumn("义乌金融供应链", "finance", "");
    }
}
