package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.MildlyPopular;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.UltraPopular;
import channelpopularity.state.Unpopular;

/**
 * @author Rashmi Badadale
 *
 * Class to get the instance of the states, impelments the StateI interface.
 */
public class SimpleStateFactory implements SimpleStateFactoryI {
    private ContextI contextI;

    /**
     * Overriding the toString() method
     * @return String
     */
    public String toString(){
        return "Creates instance of the state given the state name";
    }

    /**
     * Method to return the instance of the state
     * @param stateName Name of the state
     * @param context context instance of ContextI type
     * @return instance of the state of type StateI
     */
    @Override
    public StateI create(StateName stateName, ContextI context) {
        StateI instantiatedState;
        contextI = context;
        instantiatedState = getStateInstance(stateName);
        return instantiatedState;
    }

    /**
     * Function to return the object of the state based on the given state name
     * @param stateName Name of the state
     * @return instance of the state of type StateI
     */

    public StateI getStateInstance(StateName stateName){
        StateI instanceOfState;
        switch(stateName) {
            case UNPOPULAR:
                instanceOfState = new Unpopular(stateName,contextI);
                break;
            case MILDLY_POPULAR:
                instanceOfState = new MildlyPopular(stateName,contextI);
                break;
            case HIGHLY_POPULAR:
                instanceOfState = new HighlyPopular(stateName,contextI);
                break;
            case ULTRA_POPULAR:
                instanceOfState = new UltraPopular(stateName,contextI);
                break;
            default:
                throw new IllegalArgumentException("Unexpected State encountered!:" + stateName);
        }
        return instanceOfState;
        }
    }

