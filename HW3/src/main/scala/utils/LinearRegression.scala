package utils

import breeze.linalg.{DenseMatrix, DenseVector, sum}
import breeze.numerics.{pow, sqrt}

class LinearRegression(iterationsNum: Int = 1000, learningRate: Double = 0.1) {

  var weights: DenseVector[Double] = _
  var bias: Double = _

  def fit(
           X: DenseMatrix[Double],
           y: DenseVector[Double],
         ): Unit = {
    weights = DenseVector.zeros[Double](X.cols)
    bias = 0.0

    for (_ <- 0 to iterationsNum) {
      val curPredict = this.predict(X)
      val residuals = curPredict - y
      val gradientVector = X.t * residuals

      weights -= (learningRate / X.rows) * gradientVector
      bias = bias - learningRate * 2 * sum(curPredict - y) / X.rows
    }
  }

  def predict(X: DenseMatrix[Double]): DenseVector[Double] =
    (X * weights) + bias

  def score(yPred: DenseVector[Double], yTrue: DenseVector[Double]): Double =
    sqrt(sum((yTrue - yPred).map(pow(_, 2))) / yTrue.length)

}
