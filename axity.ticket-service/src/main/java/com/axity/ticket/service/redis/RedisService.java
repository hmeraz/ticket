package com.axity.ticket.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@Slf4j
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    public RedisService(StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    // Convertir el objeto a JSON y guardarlo en Redis
    public void save(String key, Object value) throws JsonProcessingException {
        String jsonValue = objectMapper.writeValueAsString(value);
        stringRedisTemplate.opsForValue().set("ticket:" + key, jsonValue, Duration.ofMinutes(1));
    }


    public <T> Optional<T> get(String key, Class<T> valueType) {
        try {
            String jsonValue = stringRedisTemplate.opsForValue().get("ticket:" + key);
            if (StringUtils.isNotBlank(jsonValue)) {
                return Optional.of(objectMapper.readValue(jsonValue, valueType));
            }
        } catch (JsonProcessingException e) {
            log.error("Error deserializando valor desde Redis para key [{}]", key, e);
        } catch (Exception e) {
            log.error("Error accediendo a Redis para key [{}]", key, e);
        }
        return Optional.empty();
    }
}
