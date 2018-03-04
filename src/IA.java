package virusordisdm1;

public class IA
{
    private IA()
    {
        System.out.println("wot?");
    }
    
    
    public static Coup minmax(Etat etat, int deep)
    {
        if (deep <= 0 || etat.isFinished())
            return new Coup(-1, null, etat);
        
        if (etat.player)
        {
            Coup coup = new Coup(Integer.MIN_VALUE);
            
            for (Coup coup2 : etat.getDefense())
            {
                Coup coup3 = deep > 1 ? minmax(etat.playDefense(coup2),deep-1) : coup2;

                if (coup3.value >= coup.value)
                {
                    coup = coup2;
                    coup.value = coup2.value;
                }
            }
            
            return coup;
        }
            
        else
        {
            Coup coup = new Coup(Integer.MAX_VALUE);
            
            for (Coup coup2 : etat.getAttack())
            {
                Coup coup3 = deep > 1 ? minmax(etat.playAttack(coup2), deep-1) : coup2;

                if (coup3.value <= coup.value)
                {
                    coup = coup2;
                    coup.value = coup2.value;
                }
            }
            
            return coup;
        }
    }
}