/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchMonitored.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.railway.Switch;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.switchMonitored pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SwitchMonitoredProcessor implements IMatchProcessor<SwitchMonitoredMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSw the value of pattern parameter sw in the currently processed match
   * 
   */
  public abstract void process(final Switch pSw);
  
  @Override
  public void process(final SwitchMonitoredMatch match) {
    process(match.getSw());
  }
}
