Блок 1. Развертывание локального кластера Hadoop
  
1) Развернуть локальный кластер в конфигурации 1 NN, 3 DN + NM, 1 RM, 1 History server ([инструкция](https://www.youtube.com/watch?v=ny2w5zImqvA))  
2) Изучить настройки и состояние NM и RM в веб-интерфейсе  
3) Сделать скриншоты NN и RM, добавить в репозиторий  
  
Выполнение:  
Был склонирован репозиторий https://github.com/big-data-europe/docker-hadoop.
docker-compose.yml был изменен на представленный в данной папке.  
 - http://localhost:9870/dfshealth.html#tab-overview - API hdfs  
 - http://localhost:8088/cluster - ресурс менеджер  
