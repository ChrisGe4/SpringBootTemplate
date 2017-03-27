package com.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.io.File;

/**
 * @author Chris.Ge
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    @StepScope
    FlatFileItemReader<User> flatFileItemReader(@Value("#{jobParameters[file]}") File file) {
        FlatFileItemReader<User> r = new FlatFileItemReader<>();
        r.setResource(new FileSystemResource(file));
        r.setLineMapper(new DefaultLineMapper<User>() {
            {
                this.setLineTokenizer(new DelimitedLineTokenizer(",") {
                    {
                        this.setNames(new String[] {"first", "last", "email"});
                    }
                });
                this.setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
                    {
                        this.setTargetType(User.class);
                    }
                });
            }
        });
        return r;
    }

    @Bean
    JdbcBatchItemWriter<User> jdbcBatchItemWriter(DataSource pg) {
        JdbcBatchItemWriter<User> w = new JdbcBatchItemWriter<>();
        w.setDataSource(pg);
        w.setSql("insert into user_t( first, last, email) values ( :first, :last, :email )");
        w.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return w;
    }

    @Bean
    Job personEtl(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
        FlatFileItemReader<User> reader, JdbcBatchItemWriter<User> writer) {

        Step step = stepBuilderFactory.get("file-to-database").<User, User>chunk(5).reader(reader)
            .writer(writer).build();

        return jobBuilderFactory.get("etl").start(step).build();
    }

    //@Bean
    //    CommandLineRunner runner(JobLauncher launcher, Job job, @Value("${file}") File in,
    //        JdbcTemplate jdbcTemplate) {
    //        return args -> {
    //
    //            JobExecution execution = launcher.run(job,
    //                new JobParametersBuilder().addString("file", in.getAbsolutePath())
    //                    .toJobParameters());
    //
    //            System.out.println("execution status: " + execution.getExitStatus().toString());
    //
    //            List<User> personList = jdbcTemplate.query("select * from USER_T",
    //                (resultSet, i) -> new User(resultSet.getString("first"),
    //                    resultSet.getString("last"), resultSet.getString("email")));
    //
    //            personList.forEach(System.out::println);
    //
    //        };

    //  }
}
