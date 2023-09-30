package org.mili.opennext;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.event.EventHandler;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PostLoginEvent event) {
        HttpClient httpClient = HttpClients.createDefault();

        // 构建GET请求
        String baseUrl = "http://面板IP:端口";
        String uuid = "实例UUID";
        String remoteUuid = "守护进程 UUID";
        String apiKey = "面板的用户的APIKEY";

        String apiUrl = baseUrl + "/api/protected_instance/open?uuid=" + uuid + "&remote_uuid=" + remoteUuid + "&apikey=" + apiKey;
        HttpGet request = new HttpGet(apiUrl);
        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("下游响应内容：" + responseBody);
            } else {
                System.err.println("下游请求失败，状态码：" + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            request.releaseConnection();
        }
    }
}
