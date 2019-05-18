package fr.ec.producthunt.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import fr.ec.producthunt.R;
import fr.ec.producthunt.data.model.Post;
import fr.ec.producthunt.ui.detail.DetailActivity;
import fr.ec.producthunt.ui.detail.DetailPostFragment;

public class MainActivity extends AppCompatActivity implements PostsFragments.Callback, NavigationView.OnNavigationItemSelectedListener {

    private boolean towPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        FrameLayout detailContainer = findViewById(R.id.home_detail_container);

        if (detailContainer != null) {
            towPane = true;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_detail_container, DetailPostFragment.getNewInstance(null))
                    .commit();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClickPost(Post post) {

        if (towPane) {
            DetailPostFragment detailPostFragment =
                    (DetailPostFragment) getSupportFragmentManager().findFragmentById(R.id.home_detail_container);

            if (detailPostFragment != null) {
                detailPostFragment.loadUrl(post.getPostUrl());
            }
        } else {

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.POST_URL_KEY, post.getPostUrl());

            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

    /*if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
