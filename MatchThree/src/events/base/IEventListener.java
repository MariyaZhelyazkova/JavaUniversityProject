package events.base;

public interface IEventListener {

    void onEvent(IEvent e);

    Integer getId();
}
