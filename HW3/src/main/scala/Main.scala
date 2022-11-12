import breeze.linalg.{csvread, csvwrite}
import utils.LinearRegression

import java.io.File
import scala.collection.mutable
import scala.sys.exit

object Main {

  object cfg {
    var trainPath: String = _
    var testPath: String = _
    var resultPath: String = _
    var learningRate: Double = _
    var iterationsNum: Int = _
  }

  val DEFAULT_ITERS: Int = 1000
  val DEFAULT_LR: Double = 0.1

  def parseArgs(args: Array[String]): Unit = {

    val trainPathParam = (
      Array("--train_path", "-tr"), // aliases of parameter, first alias - storing key in argMap
      "path to train dataset file in csv format (with doubles only)" // param description
    )
    val testPathParam = (
      Array("--test_path", "-ts"),
      "path to test data file in csv format (with doubles only); if " +
        "it provides target column too - RMSE will be calculated"
    )
    val resultPathParam = (
      Array("--result_path", "-r"), "path for saving predictions vector"
    )
    val learningRateParam = (
      Array("--learning_rate", "-lr"), s"regression learning rate, default is $DEFAULT_LR"
    )
    val iterationsNumParam = (
      Array("--iterations_num", "-i"), s"number of regression steps, default value is $DEFAULT_ITERS"
    )

    def getArgMapKey(paramDescriptor: (Array[String], String)): String = paramDescriptor._1(0)

    val paramList: List[(Array[String], String)] = List(
      trainPathParam, testPathParam, resultPathParam, learningRateParam, iterationsNumParam
    )

    if (args.isEmpty || args.length % 2 != 0) {
      args match {
        case Array("-h") | Array("--help") =>
          println("App for Linear regression. Parameters:")
          for (param <- paramList) println(s"${param._1.mkString(", ")} - ${param._2}")
        case default =>
          println("Failed to parse input params, for getting help use -h or --help")
      }
      exit(1)
    }

    val argMapBuilder = mutable.Map.newBuilder[String, String]

    //setting default values
    argMapBuilder += getArgMapKey(learningRateParam) -> DEFAULT_LR.toString
    argMapBuilder += getArgMapKey(iterationsNumParam) -> DEFAULT_ITERS.toString

    args.sliding(2, 2).toList.foreach {
      case Array(name, value) =>

        val paramDesriptor = try {
          paramList.find(_._1.contains(name)).get
        } catch {
          case err: Exception =>
            println(s"Failed to parse parameter $name $value\nFor getting help use -h or --help")
            throw err
        }
        argMapBuilder += getArgMapKey(paramDesriptor) -> value
    }

    val argMap: mutable.Map[String, String] = argMapBuilder.result()

    def getArgValueFromArgMap(paramDescriptor: (Array[String], String)): String =
      argMap.getOrElse(
        getArgMapKey(paramDescriptor),
        throw new Exception(s"param ${paramDescriptor._1.mkString(", ")} is required")
      )

    try {
      cfg.trainPath = getArgValueFromArgMap(trainPathParam)
      cfg.testPath = getArgValueFromArgMap(testPathParam)
      cfg.resultPath = getArgValueFromArgMap(resultPathParam)

      try {
        cfg.learningRate = getArgValueFromArgMap(learningRateParam).toDouble
      } catch {
        case err: java.lang.NumberFormatException =>
          println("Failed to parse learning rate param, input double")
          throw err
      }

      try {
        cfg.iterationsNum = getArgValueFromArgMap(iterationsNumParam).toInt
      } catch {
        case err: java.lang.NumberFormatException =>
          println("Failed to parse learning rate param, input double")
          throw err
      }

    } catch {
      case err: Exception => println(err.getMessage)
        println()
        throw err
    }
  }

  def main(args: Array[String]): Unit = {

    parseArgs(args)

    val trainData = csvread(file = new File(cfg.trainPath), separator = ',')
    val testData = csvread(file = new File(cfg.testPath), separator = ',')

    val xTrain = trainData(::, 0 until trainData.cols - 1)
    val yTrain = trainData(::, trainData.cols - 1).toDenseVector

    val model = new LinearRegression(cfg.iterationsNum, cfg.learningRate)
    model.fit(xTrain, yTrain)

    val xTest = testData(::, 0 until trainData.cols - 1)
    val yPred = model.predict(xTest)

    if (testData.cols == trainData.cols) {
      val yTest = testData(::, testData.cols - 1).toDenseVector
      println(s"Score RMSE: ${model.score(yPred, yTest)}")
    }

    println(s"Weights: ${model.weights.toArray.mkString(", ")}")
    println(s"Bias: ${model.bias}")

    csvwrite(new File(cfg.resultPath), separator = ',', mat = yPred.toDenseMatrix.t)
  }


}