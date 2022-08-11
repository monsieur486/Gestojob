const gulp = require('gulp');
const sass = require('gulp-sass')(require('sass'));
const uglify = require('gulp-uglify');
const concat = require('gulp-concat');
const imagemin = require('gulp-imagemin');


gulp.task('sass', function(done){
    gulp.src('public/sass/**/*.scss')
        .pipe(sass({outputStyle: 'compressed'})) // Using gulp-sass
        .pipe(gulp.dest('src/main/resources/static/css'))
    done();
});


gulp.task('scripts', function(done){
    gulp.src('public/javascript/**/*.js')
        .pipe(concat('scripts.js'))
        .pipe(uglify())
        .pipe(gulp.dest('src/main/resources/static/js'))
    done();
});


gulp.task('images', function(done){
    gulp.src('public/image/*.{png,jpg,jpeg,gif,svg}')
        .pipe(imagemin())
        .pipe(gulp.dest('src/main/resources/static/img'))
    done();
});


gulp.task('watch', function(done){
    gulp.watch('public/sass/**/*.scss', gulp.series('sass') )
    gulp.watch('public/javascript/**/*.js', gulp.series('scripts') )
    gulp.watch('public/image/*.{png,jpg,jpeg,gif,svg}', gulp.series('images') )
    done();
});
