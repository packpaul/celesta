package ru.curs.celesta.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import ru.curs.celesta.CelestaException;
import ru.curs.celesta.score.*;
import ru.curs.celesta.score.discovery.DefaultScoreDiscovery;

import java.io.File;
import java.util.*;

import static ru.curs.celesta.plugin.CursorGenerator.generateCursor;

@Mojo(
        name = "gen-cursors",
        defaultPhase = LifecyclePhase.GENERATE_SOURCES
)
public class GenCursorsMojo extends AbstractMojo {

    @Parameter(property = "scores", required = true)
    protected List<ScoreProperties> scores;

    @Parameter(property = "genSysCursors")
    protected boolean genSysCursors;

    @Component
    private MavenProject project;

    @Override
    public void execute() {
        System.out.println("celesta project is " + project);
        this.scores.forEach(this::processScore);
        this.addSourceRoot(getSourceRoot());
    }

    private void processScore(ScoreProperties properties) {
        Score score = initScore(properties.getPath());
        score.getGrains().values()
                .stream()
                .filter(g -> genSysCursors || !g.getScore().getSysSchemaName().equals(g.getName()))
                .forEach(g -> generateCursors(g, score));
    }

    private Score initScore(String scorePath) {
        try {
            Score score = new AbstractScore.ScoreBuilder<>(Score.class)
                    .path(scorePath)
                    .scoreDiscovery(new DefaultScoreDiscovery())
                    .build();
            return score;
        } catch (CelestaException | ParseException e) {
            throw new CelestaException("Can't init score", e);
        }

    }

    private void generateCursors(Grain g, Score score) {
        final boolean isSysSchema = g.getName().equals(g.getScore().getSysSchemaName());
        Map<GrainPart, List<GrainElement>> partsToElements = new HashMap<>();

        List<GrainElement> elements = new ArrayList<>();
        elements.addAll(g.getElements(SequenceElement.class).values());
        elements.addAll(g.getElements(Table.class).values());
        elements.addAll(g.getElements(View.class).values());
        elements.addAll(g.getElements(MaterializedView.class).values());
        elements.addAll(g.getElements(ParameterizedView.class).values());

        elements.forEach(
                ge -> partsToElements.computeIfAbsent(ge.getGrainPart(), gp -> new ArrayList())
                        .add(ge)
        );


        partsToElements.entrySet().stream().forEach(
                e -> {
                    final String scorePath;
                    if (isSysSchema) {
                        scorePath = "";
                    } else {
                        final String grainPartPath = e.getKey().getSourceFile().getAbsolutePath();
                        final String scoreRelativeOrAbsolutePath = Arrays.stream(score.getPath()
                                .split(File.pathSeparator)).filter(
                                path -> grainPartPath.contains(new File(path).getAbsolutePath())
                        )
                                .findFirst().get();
                        File scoreDir = new File(scoreRelativeOrAbsolutePath);
                        scorePath = scoreDir.getAbsolutePath();
                    }
                    e.getValue().forEach(
                            ge -> generateCursor(ge, getSourceRoot(), scorePath)
                    );
                }

        );

    }

    private File getSourceRoot() {
        return new File(
                project.getBuild().getDirectory()
                        + File.separator + "generated-sources" + File.separator + "celesta"
        );
    }


    private void addSourceRoot(File directory) {
        if (this.project != null) {
            this.getLog().info("Adding compile source root for cursors: " + directory);
            this.project.addCompileSourceRoot(directory.getAbsolutePath());
        }
    }

}
