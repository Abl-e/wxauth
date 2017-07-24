import org.junit.Test;
import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by 唐国翔 on 2017/7/20 0020.
 */
public class ConnectionTest {
    @Test
    public void connection() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
    }
}
