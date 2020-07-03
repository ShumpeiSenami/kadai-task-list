
import com.google.inject.AbstractModule
import services.{TaskServices, TaskServiceImpl}


class AppModule extends AbstractModule{
  override def configure(): Unit = {
    bind(classOf[TaskServices]).to(classOf[TaskServiceImpl])
  }

}
