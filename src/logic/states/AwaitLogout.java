package logic.states;

import logic.E2UData;

public class AwaitLogout extends StateAdapter {

    public AwaitLogout(E2UData data) {
        this.data = data;
    }

    @Override
    public IStates Logout() {
        return super.Logout();
    }

}
