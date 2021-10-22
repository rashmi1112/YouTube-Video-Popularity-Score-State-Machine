package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

/**
 * Interface to be implemented by the SimpleStateFactory class
 */

public interface SimpleStateFactoryI {
  StateI create(StateName stateName, ContextI context);
}
