package logic.states;

public class AwaitRegister extends StateAdapter {

    @Override
    public IStates Registo(String sUsername, String sPassword, String sConPassord) {

        if (!sUsername.isEmpty() && !sPassword.isEmpty() && !sConPassord.isEmpty())
            return null;

        return new AwaitRegister();
    }

}
