# Ground


# CDC

```shell
$ mkdir -p mysql/conf.d mysql/init mongodb/init
```


로깅 시스템:

ELK 스택(Elasticsearch, Logstash, Kibana) 또는 EFK 스택(Elasticsearch, Fluentd, Kibana)
모든 서비스의 로그를 중앙 집중화하여 모니터링 및 분석


모니터링 및 알림 시스템:

Prometheus + Grafana에 알림 매니저(AlertManager) 추가
시스템 메트릭, 애플리케이션 메트릭, 비즈니스 메트릭 모니터링


서비스 디스커버리 및 설정 관리:

Consul, etcd 또는 Spring Cloud Config Server
중앙화된 설정 관리 및 서비스 디스커버리


API 게이트웨이/서비스 메시:

Kong, Traefik, Istio, Linkerd 등
라우팅, 로드 밸런싱, 서킷 브레이커, 인증/인가 등 처리


보안 강화:

Vault - 시크릿 관리
OAuth2/OIDC 인증 서버
네트워크 정책 및 방화벽 설정


백업 및 복구 시스템:

데이터베이스 자동 백업
MinIO 데이터 백업
장애 복구 시나리오 및 자동화


분산 트레이싱:

Jaeger 또는 Zipkin
마이크로서비스 간 요청 흐름 추적


CI/CD 파이프라인:

Jenkins, GitLab CI/CD, GitHub Actions 등
자동화된 빌드, 테스트, 배포


메시지 큐 확장:

RabbitMQ 또는 Kafka를 이용한 이벤트 기반 아키텍처
시스템 간 비동기 통신


데이터베이스 고가용성:

MySQL 복제 및 클러스터링
MongoDB 복제셋
Redis 클러스터 또는 센티널


컨테이너 오케스트레이션:

Docker Compose에서 Kubernetes로 마이그레이션 고려
스케일링, 자동 복구, 롤링 업데이트 등 활용