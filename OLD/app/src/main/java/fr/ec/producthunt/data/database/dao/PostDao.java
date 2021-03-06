package fr.ec.producthunt.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import fr.ec.producthunt.data.model.Post;
import java.util.List;

@Dao
public interface   PostDao {
  @Query("SELECT * FROM posts")
  List<Post> getPosts();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void save(List<Post> posts);
}
