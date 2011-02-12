package cs224n.assignments;

import java.util.List;
import java.util.Map;

import cs224n.classify.ClassifierFactory;
import cs224n.classify.Datum;
import cs224n.classify.LabeledDatum;
import cs224n.classify.ProbabilisticClassifier;
import cs224n.util.Counter;

/**
 * @author Dan Klein
 */
public class MostFrequentLabelClassifier<F,L> implements ProbabilisticClassifier<F,L> {
  public static class Factory<F,L> implements ClassifierFactory<F,L> {
    public ProbabilisticClassifier<F,L> trainClassifier(List<LabeledDatum<F,L>> trainingData) {
      Counter<L> labelCounter = new Counter<L>();
      for (LabeledDatum<F,L> datum : trainingData) {
        labelCounter.incrementCount(datum.getLabel(), 1.0);
      }
      L mostFrequentLabel = labelCounter.argMax();
      return new MostFrequentLabelClassifier<F,L>(mostFrequentLabel);
    }
  }

  L mostFrequentLabel;

  public Counter<L> getProbabilities(Datum<F> datum) {
    Counter<L> counter = new Counter<L>();
    counter.setCount(getLabel(datum), 1.0);
    return counter;
  }

  public Counter<L> getLogProbabilities(Datum<F> datum) {
    throw new UnsupportedOperationException();
  }
  
  public L getLabel(Datum<F> datum) {
    return mostFrequentLabel;
  }

  public MostFrequentLabelClassifier(L mostFrequentLabel) {
    this.mostFrequentLabel = mostFrequentLabel;
  }
}
