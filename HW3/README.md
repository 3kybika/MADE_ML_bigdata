# HW3: Линейная регрессия на Scala
Описание работы и критериев оценивания  
В рамках данного домашнего задания предлагается реализовать алгоритм линейной регрессии на Scala с использованием
библиотеки Breeze.  
Решение можно оформить в одном из двух вариантов:  
- в виде ноутбука на Scala (75 баллов)  
- в виде полноценного проекта на Scala (100 баллов)
  Решение должно принимать на вход тренировочные и тестовые данные в виде файлов. Выход решения - предсказание модели
  предлагается также реализовать через файл. В процессе обучения модели рекомендуется организовать валидацию и
  сохранять вывод ее результатов.  
  В выборе данных и формате датасета не ограничиваем, можно взять простой вариант с 3-4 числовыми фичами.
  Решение, данные, результат работы алгоритма и вывод валидации добавить в репозиторий на github и прислать ссылку в
  интерфейсе MADE.  
  Бонусы и штрафы:  
  ● 100% за плагиат в решениях (всем участникам процесса)  
  ● 30% за посылку решения в течение недели после deadline  
  ● 10% за несоответствие правилам оформления задачи  
  
Для генерации данных использовался ноутбук: [data_generator.ipynb](https://github.com/3kybika/MADE_ML_bigdata/blob/main/HW3/data_generator.ipynb)
Для запуска линейной регресси на скале:
```  
sbt > run -tr ./data/train.csv --test_path ./data/test.csv -r ./data/result.csv
```
Параметры:
```    
App for Linear regression. Parameters:
--train_path, -tr - path to train dataset file in csv format (with doubles only)
--test_path, -ts - path to test data file in csv format (with doubles only); if it provides target column too - RMSE will be calculated
--result_path, -r - path for saving predictions vector
--learning_rate, -lr - regression learning rate, default is 0.1
--iterations_num, -i - number of regression steps, default value is 1000
```

Вывод программки:
```
Score RMSE: 9.947487610706341
Weights: 95.99130386626102, 56.14822089970876, 81.70602147819217, 33.90383322177734
Bias: 199.94309577744153
```
Истинные значения коэффициентов, использованне при генерации данных:
```
Score RMSE: 9.947487610706336
Weights: 96.126339 56.063498 81.675645 34.083171
Bias: 200.0
```

