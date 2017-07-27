import org.junit.Test;
import util.DBUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by 唐国翔 on 2017/7/20 0020.
 */
public class ConnectionTest {
    @Test
    public void connection() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test2(){
        out:
        for (int i = 0; i < 100; i++) {
            if(i!=0&&i%10==0){
                break out;
            }
            System.out.println("-->"+i);
        }
    }

    @Test
    public void test3(){
        int [][] sanjiao = new int[5][];
        for (int i = 0; i < 5; i++) {
            sanjiao[i] = new int[i+1];
            sanjiao[i][0] = 1;
            sanjiao[i][i] = 1;
            for (int j = 1; j < i; j++) {
                sanjiao[i][j] = sanjiao[i-1][j-1] + sanjiao[i-1][j];
            }
        }
        for (int[] row:sanjiao) {
            for(int ele:row){
                System.out.print(ele+"  ");
            }
            System.out.println();
        }
    }

    @Test
    public void testDate(){
    }
}
