
package com.alessandraipiranga.backend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan(basePackages = {"com.alessandraipiranga.backend.model"})
@EnableJpaRepositories(basePackages = {"com.alessandraipiranga.backend.repo"})
@EnableTransactionManagement
public class JpaConfig {
}