package biz.gabrys.agabrys.sonarqube.falsepositives.d20181102;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

/**
 * @see https://community.sonarsource.com/t/s2095-resources-should-be-closed-should-ignore-jchannel-instances-jgroups/3922?u=agabrys
 */
public class JChannelAndS2095 {

    private volatile JChannel channel;

    public void init(final String configuration) throws Exception {
        this.channel = openChannel(configuration);
    }

    public void shutdown() {
        if (channel != null) {
            try {
                channel.close();
            } finally {
                channel = null;
            }
        }
    }

    private JChannel openChannel(final String configuration) throws Exception {
        final JChannel jChannel = new JChannel(configuration);
        jChannel.setName("name");
        jChannel.connect("channel-name");
        jChannel.setReceiver(new ReceiverAdapter() {
            @Override
            public void viewAccepted(final View view) {
                // do something
            }

            @Override
            public void receive(final Message msg) {
                // do something
            }
        });
        return jChannel;
    }
}
